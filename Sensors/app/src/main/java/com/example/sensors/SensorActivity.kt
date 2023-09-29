package com.example.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sensors.databinding.ActivitySensorBinding

class SensorActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivitySensorBinding
    private lateinit var sensorManager: SensorManager

    private lateinit var accelerometer: Sensor
    private lateinit var light: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySensorBinding.inflate(layoutInflater)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        with(sensorManager) {
            registerListener(this@SensorActivity, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            registerListener(this@SensorActivity, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when(event.sensor) {
            accelerometer -> {
                Log.d("Sensor", "Accelerometer: x=${event.values[0]}, y=${event.values[1]}, z=${event.values[2]}")
            }
            light -> {
                binding.lightValue.text = "${event.values[0]} lx"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
}