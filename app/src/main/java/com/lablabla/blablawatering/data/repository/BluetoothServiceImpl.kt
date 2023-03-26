package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.mapper.toBluetoothDevice
import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.RemoteCommands
import com.lablabla.blablawatering.domain.repository.BluetoothService
import com.lablabla.blablawatering.util.Resource
import com.welie.blessed.BluetoothCentralManager
import com.welie.blessed.ConnectionFailedException
import com.welie.blessed.ConnectionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BluetoothServiceImpl @Inject constructor(
    private val central: BluetoothCentralManager
) : BluetoothService {

    private val connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    var _connectionState = connectionState.asStateFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var connectionCallback: ((Device, Boolean) -> Unit)? = null

    // Services & Characteristics
    private val SERVICE_UUID =                          UUID.fromString("0000abcd-6e32-4f94-adf6-b96ebda4c6ce")

    private val SET_DATA_UUID =                         UUID.fromString("1000b0ea-6e32-4f94-adf6-b96ebda4c6ce")
    private val GET_STATIONS_UUID =                     UUID.fromString("1001b0ea-6e32-4f94-adf6-b96ebda4c6ce")
    private val GET_EVENTS_UUID =                       UUID.fromString("1002b0ea-6e32-4f94-adf6-b96ebda4c6ce")
    private val NOTIFY_STATION_STATUS_CHANGED_UUID =    UUID.fromString("1003b0ea-6e32-4f94-adf6-b96ebda4c6ce")

    init {
        central.observeConnectionState { peripheral, state ->
            Timber.i("Peripheral ${peripheral.name} has $state")
            connectionCallback?.invoke(peripheral.toBluetoothDevice(), state == ConnectionState.CONNECTED)
        }
    }

    override fun scanBleDevices() {
        central.scanForPeripheralsWithServices(arrayOf(SERVICE_UUID),
            { peripheral, scanResult ->
                Timber.i("Found peripheral '${peripheral.name}' with RSSI ${scanResult.rssi}")
                central.stopScan()
                connectToDevice(peripheral.toBluetoothDevice())
            },
            {
                Timber.e("Failed scanning with error $it")
            })
    }

    override fun stopScan() {
        central.stopScan()
    }

    override fun connectToDevice(device: Device) {
        scope.launch {
            try {
                val peripheral = central.getPeripheral(device.address)
                central.connectPeripheral(peripheral)
            } catch (connectionFailed: ConnectionFailedException) {
                Timber.e(connectionFailed)
                connectionFailed.printStackTrace()
            }
        }
    }

    override fun addOnDeviceConnected(callback: (Device, Boolean) -> Unit) {
        connectionCallback = callback
    }

    override suspend fun sendCommand(device: Device, commands: RemoteCommands, data: ByteArray?) {
        TODO("Not yet implemented")
    }
}