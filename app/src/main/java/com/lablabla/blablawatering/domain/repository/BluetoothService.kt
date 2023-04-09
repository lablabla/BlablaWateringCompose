package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.RemoteCommands
import com.lablabla.blablawatering.domain.model.Zone

interface BluetoothService {

    fun scanBleDevices()

    fun stopScan()

    fun connectToDevice(device: Device)

    fun addOnDeviceConnected(callback: (Device, Boolean) -> Unit)

    fun addOnZonesChanged(callback: (Device, List<Zone>) -> Unit)

    suspend fun sendCommand(commands: RemoteCommands, data: ByteArray? = null)
}