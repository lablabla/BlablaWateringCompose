package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.mapper.toBluetoothDevice
import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.RemoteCommands
import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.repository.BluetoothService
import com.lablabla.blablawatering.domain.repository.JSONHandler
import com.welie.blessed.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BluetoothServiceImpl @Inject constructor(
    private val central: BluetoothCentralManager,
    private val jsonHandler: JSONHandler
) : BluetoothService {

    private val connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    var _connectionState = connectionState.asStateFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var connectionCallback: ((Device, Boolean) -> Unit)? = null

    private var notificationCallback: ((Device, List<Zone>) -> Unit)? = null

    private var _peripheral: BluetoothPeripheral? = null

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
            if (state == ConnectionState.DISCONNECTED) {
                scanBleDevices()
            }
            else if (state == ConnectionState.CONNECTED) {
                _peripheral = peripheral
            }
            scope.launch {
                if (state == ConnectionState.CONNECTED) {
                    val newMtu = peripheral.requestMtu(BluetoothPeripheral.MAX_MTU)
                    Timber.i("New MTU set to $newMtu")
                }
                peripheral.getCharacteristic(SERVICE_UUID, NOTIFY_STATION_STATUS_CHANGED_UUID)
                    ?.let {
                        peripheral.observe(it) { value ->
                            val json = value.asString()
                            Timber.i(json)
                            jsonHandler.parseZones(json)?.let { stations ->
//                                notificationCallback?.invoke(
//                                    peripheral.toBluetoothDevice(),
//                                    stations
//                                )
                            }
                        }
                    }
            }
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

    override fun addOnZonesChanged(callback: (Device, List<Zone>) -> Unit) {
        notificationCallback = callback
    }

    override suspend fun sendCommand(commands: RemoteCommands, data: ByteArray?) {
        _peripheral?.let {
            getStations(it)
        }
    }

    private suspend fun getStations(peripheral: BluetoothPeripheral) {
        peripheral.getCharacteristic(SERVICE_UUID, GET_STATIONS_UUID)?.let {
            val json = peripheral.readCharacteristic(it).asString()
            Timber.i(json)
            jsonHandler.parseZones(json)?.let { stations ->
                notificationCallback?.invoke(
                    peripheral.toBluetoothDevice(),
                    stations
                )
            }
        }
    }
}