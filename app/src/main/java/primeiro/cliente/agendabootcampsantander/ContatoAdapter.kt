package primeiro.cliente.agendabootcampsantander

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ContatoAdapter(val contatosList: ArrayList<Contato>) :
    RecyclerView.Adapter<ContatoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contato_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoAdapter.ViewHolder, position: Int) {
        holder.bindItem(contatosList[position])
    }


    override fun getItemCount(): Int {
        return contatosList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(contato: Contato) {
            val textNome = itemView.findViewById<TextView>(R.id.contato_nome)
            val textTelefone = itemView.findViewById<TextView>(R.id.contato_telefone)

            textNome.text = contato.nome
            textTelefone.text = contato.numeroTelefone


        }

    }
}