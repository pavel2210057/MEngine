package org.mlib.Network;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import org.mlib.System.DeviceService.MDevice;
import org.mlib.System.Exception.MException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

public class MBluetooth {
    private BluetoothAdapter adapter;
    private BluetoothSocket socket;
    private String activeAddress;
    private UUID uuid;

    public MBluetooth() {
        setAdapter(BluetoothAdapter.getDefaultAdapter());
        setUuid(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
    }

    public void setAdapter(BluetoothAdapter adapter) {
        this.adapter = adapter;
    }

    public void setAddress(String address) {
        this.activeAddress = address;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void require(MDevice device, String action) {
        Intent intent = new Intent(action);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 500);
        device.getActivity().startActivityForResult(intent, 0);
    }

    public boolean isOn() {
        if (this.adapter.getState() != BluetoothAdapter.STATE_ON) {
            new MException("Bluetooth выключен").printStackTrace();
            return false;
        }

        return true;
    }

    public boolean isReady() {
        return isOn() && checkSocket();
    }

    public boolean isEnabled() {
        if(!this.adapter.isEnabled()) {
            new MException("Устройство не поддерживает Bluetooth").printStackTrace();
            return false;
        }

        return true;
    }

    public void on(MDevice device) {
        if (!isOn())
            require(device, BluetoothAdapter.ACTION_REQUEST_ENABLE);
    }

    public void discovery(MDevice device) {
        require(device, BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    }

    public void stopDiscovery() {
        this.adapter.cancelDiscovery();
    }

    public BluetoothAdapter getAdapter() {
        return this.adapter;
    }

    public BluetoothSocket getSocket() {
        return this.socket;
    }

    public String getAddress() {
        return this.activeAddress;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public HashMap<String, String> getBondedPairs() {
        HashMap<String, String> pairs = new HashMap<>();

        for (BluetoothDevice device : this.adapter.getBondedDevices())
            pairs.put(device.getName(), device.getAddress());

        return pairs;
    }

    public BluetoothSocket listen() {
        return listen(this.activeAddress);
    }

    public BluetoothSocket listen(int timeout) {
        return listen(this.activeAddress, timeout);
    }

    public BluetoothSocket listen(String address) {
        return listen(address, 0);
    }

    public BluetoothSocket listen(String address, int timeout) {
        if (!isOn() || !checkDevice(address))
            return null;

        disconnect();
        stopDiscovery();

        try {
            BluetoothServerSocket serverSocket =
                    this.adapter.listenUsingInsecureRfcommWithServiceRecord(address, this.uuid);
            BluetoothSocket socket;

            if (timeout > 0)
                socket = serverSocket.accept(timeout);
            else
                socket = serverSocket.accept();

            this.socket = socket;
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BluetoothSocket connect() {
        return connect(this.activeAddress);
    }

    public BluetoothSocket connect(String address) {
        if (!isOn() || !checkDevice(address))
            return null;

        disconnect();
        stopDiscovery();

        BluetoothDevice device = this.adapter.getRemoteDevice(address);

        try {
            BluetoothSocket socket =
                    device.createInsecureRfcommSocketToServiceRecord(this.uuid);
            socket.connect();

            this.socket = socket;
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        if (!checkSocket())
            return;

        try {
            this.socket.close();
            this.socket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BluetoothDevice getActiveDevice() {
        if (!isReady())
            return null;

        return this.socket.getRemoteDevice();
    }

    public InputStream getInputStream() {
        if (!isReady())
            return null;

        try {
            return this.socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OutputStream getOutputStream() {
        if (!isReady())
            return null;

        try {
            return this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void send(String data) {
        send(data.getBytes());
    }

    public void send(byte[] bytes) {
        send(bytes, 0, bytes.length);
    }

    public void send(byte[] bytes, int offset, int length) {
        if (!isReady())
            return;

        try {
            this.socket.getOutputStream().write(bytes, offset, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        if (!isReady())
            return null;

        StringBuilder builder = new StringBuilder();
        try {
            InputStream stream = this.socket.getInputStream();
            int bufferSize = 1024, byteCount;
            byte[] buffer = new byte[bufferSize];

            do {
                byteCount = stream.read(buffer);
                builder.append(new String(buffer));
            } while (byteCount == bufferSize);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return builder.substring(0, builder.indexOf("\0"));
    }

    private boolean checkDevice(String address) {
        if (!BluetoothAdapter.checkBluetoothAddress(address)) {
            new MException("Bluetooth-устройство не найдено").printStackTrace();
            return false;
        }

        return true;
    }

    private boolean checkAddress() {
        if (this.activeAddress.length() == 0) {
            new MException("Активный адрес не определен").printStackTrace();
            return false;
        }

        return true;
    }

    private boolean checkSocket() {
        if (this.socket == null) {
            new MException("Сокет не определен").printStackTrace();
            return false;
        }

        return true;
    }
}