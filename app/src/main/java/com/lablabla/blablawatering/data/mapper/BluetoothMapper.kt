package com.lablabla.blablawatering.data.mapper

import com.lablabla.blablawatering.domain.model.Device
import com.welie.blessed.BluetoothPeripheral

fun BluetoothPeripheral.toBluetoothDevice() : Device {
    return Device(name, address)
}