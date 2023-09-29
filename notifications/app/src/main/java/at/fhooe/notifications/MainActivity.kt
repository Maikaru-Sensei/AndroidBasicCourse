package at.fhooe.notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import at.fhooe.notifications.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            btnSnackbarShort.setOnClickListener(this@MainActivity)
            btnSnackbarLong.setOnClickListener(this@MainActivity)
            btnToastShort.setOnClickListener(this@MainActivity)
            btnToastLong.setOnClickListener(this@MainActivity)
            btnNotificationNew.setOnClickListener(this@MainActivity)
            btnNotificationUpd.setOnClickListener(this@MainActivity)
        }

        requestNotificationPermission()

        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.btnSnackbarShort.id -> {
                val emailSentSnackbar = Snackbar
                    .make(binding.notificationLayout, resources.getString(R.string.email_sent), Snackbar.LENGTH_SHORT)
                    .setAction(resources.getString(R.string.undo_email_sent)) {
                        Toast.makeText(this, "undone (short)", Toast.LENGTH_SHORT).show()
                    }

                emailSentSnackbar.show()
            }

            binding.btnSnackbarLong.id -> {
                val emailSentSnackbar = Snackbar
                    .make(binding.notificationLayout, resources.getString(R.string.email_sent), Snackbar.LENGTH_LONG)
                    .setAction(resources.getString(R.string.undo_email_sent)) {
                        Toast.makeText(this, "undone (long)", Toast.LENGTH_LONG).show()
                    }

                emailSentSnackbar.show()
            }

            binding.btnToastShort.id -> {
                Toast.makeText(this, resources.getString(R.string.start_dl), Toast.LENGTH_SHORT).show()
            }

            binding.btnToastLong.id -> {
                Toast.makeText(this, resources.getString(R.string.start_dl), Toast.LENGTH_LONG).show()
            }

            binding.btnNotificationNew.id -> {
                notifyNotification(
                    id = randomNumber(),
                    notification = createNotification(
                        title = "New Notification",
                        body = "New Value: ${randomNumber()}")
                )
            }

            binding.btnNotificationUpd.id -> {
                notifyNotification(
                    id = 100,
                    notification = createNotification(
                        title = "Update Notification",
                        body = "Updated Value: ${randomNumber()}")
                )
            }
        }
    }

    private fun createNotification(title: String, body: String): Notification {
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(PendingIntent.getActivity(
                this, 0, Intent(this, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            ))
            .setSmallIcon(R.drawable.ic_message)

        return notificationBuilder.build()
    }

    private fun notifyNotification(id: Int, notification: Notification) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermission()
            return
        }

        ActivityResultContracts.RequestPermission()

        NotificationManagerCompat.from(this).notify(id, notification)
    }

    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1);
        }
    }

    private fun randomNumber() = (101..5000).random()
}