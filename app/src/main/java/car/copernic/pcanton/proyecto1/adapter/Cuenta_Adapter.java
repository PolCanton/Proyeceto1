package car.copernic.pcanton.proyecto1.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import car.copernic.pcanton.proyecto1.Modelo.cuenta;
import car.copernic.pcanton.proyecto1.R;

public class Cuenta_Adapter extends FirestoreRecyclerAdapter<cuenta, Cuenta_Adapter.ViewHolder> {

    public Cuenta_Adapter(@NotNull FirestoreRecyclerOptions<cuenta> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Cuenta_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull cuenta model) {
        holder.correo.setText("COrreo electronico:"+model.getCorreo());
        holder.nombre.setText("Nombre: "+model.getNombre());
        holder.telefono.setText("Numero telefono: "+model.getTelefono());
        holder.direccion.setText("Direccion: "+model.getDireccion());
    }

    @NonNull
    @Override
    public Cuenta_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_info_cuenta,parent,false);
        return new Cuenta_Adapter.ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,telefono,direccion,correo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.emailnombre);
            telefono=itemView.findViewById(R.id.emailtelefono);
            direccion=itemView.findViewById(R.id.emaildireccion);
            correo=itemView.findViewById(R.id.emailcorreo);

        }
    }
}
