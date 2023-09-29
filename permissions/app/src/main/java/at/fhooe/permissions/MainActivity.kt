package at.fhooe.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import at.fhooe.permissions.databinding.ActivityMainBinding
import java.security.Permission

const val PHONE_PERMISSION = 142
const val CAMERA_PERMISSION = 143

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            btnCamera.setOnClickListener(this@MainActivity)
            btnPhone.setOnClickListener(this@MainActivity)
        }

        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            PHONE_PERMISSION -> {
                if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Phone granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Phone not granted", Toast.LENGTH_LONG).show()
                }
            }
            CAMERA_PERMISSION -> {
                if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Camera not granted", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.btnCamera.id -> {
                askForPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION)
            }

            binding.btnPhone.id -> {
                askForPermission(Manifest.permission.CALL_PHONE, PHONE_PERMISSION)
            }
        }
    }

    private fun askForPermission(permission: String, requestCode: Int) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {

            }
            shouldShowRequestPermissionRationale(permission) -> {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage("This App needs this Permission access why...")
                    .setPositiveButton("OK") { _, _ ->
                        requestPermissions(arrayOf(permission), requestCode)
                    }
                    .setNegativeButton("No, thank you") { _, _ -> }
                    .create().show()
            }
            else /* Permission not requested */ -> {
                requestPermissions(arrayOf(permission), requestCode)
            }
        }
    }
}









