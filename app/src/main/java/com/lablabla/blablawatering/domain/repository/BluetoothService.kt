package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.RemoteCommands

interface BluetoothService {

    fun scanBleDevices()

    fun stopScan()

    fun connectToDevice(device: Device)

    fun addOnDeviceConnected(callback: (Device, Boolean) -> Unit)

    suspend fun sendCommand(device: Device, commands: RemoteCommands, data: ByteArray? = null)
}