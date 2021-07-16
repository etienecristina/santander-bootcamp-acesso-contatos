package primeiro.cliente.agendabootcampsantander

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CONTATO = 1
    val LINEAR_LAYOUT_VERTICAL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CONTATO
            )

        } else {
            setContatos()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CONTATO) setContatos()

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setContatos() {
        val contatoList: ArrayList<Contato> = ArrayList()

        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contatoList.add(
                    Contato(
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    )
                )
            }

            cursor.close()
        }

        val adapter = ContatoAdapter(contatoList)
        val contatoRecycleView = findViewById<RecyclerView>(R.id.contatos_recycler_view)

        contatoRecycleView.layoutManager = LinearLayoutManager(
            this,
            LINEAR_LAYOUT_VERTICAL,
            false
        )
        contatoRecycleView.adapter = adapter
    }
}