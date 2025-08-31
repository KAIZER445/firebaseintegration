package own.project.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : androidx.appcompat.app.AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var buttonSend: Button
    lateinit var textViewName: TextView

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference: DatabaseReference = database.reference.child("users")
    val reference2: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        buttonSend = findViewById(R.id.buttonSend)
        textViewName = findViewById(R.id.textViewName)

        reference2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val realName: String = snapshot.child("users").child("name").value as String
                textViewName.text = realName

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        buttonSend.setOnClickListener {
            val userName = editTextName.text.toString()
            reference.child("userName").setValue(userName)
        }
    }
}
