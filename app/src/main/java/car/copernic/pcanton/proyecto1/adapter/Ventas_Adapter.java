package car.copernic.pcanton.proyecto1.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import car.copernic.pcanton.proyecto1.Modelo.Anuncio;
import car.copernic.pcanton.proyecto1.Modelo.Venta;
import car.copernic.pcanton.proyecto1.R;
import car.copernic.pcanton.proyecto1.mostrar_anuncio.mostrar_anuncio;

public class Ventas_Adapter extends FirestoreRecyclerAdapter<Venta, Ventas_Adapter.ViewHolder> {

    public Ventas_Adapter(@NotNull FirestoreRecyclerOptions<Venta> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Ventas_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Venta model) {
        holder.producto.setText(model.getIdproducto());
        holder.direccion.setText(model.getDireccion());
    }

    @NonNull
    @Override
    public Ventas_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ventas,parent,false);
        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView producto;
        TextView direccion;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            producto=itemView.findViewById(R.id.textView_nombre_producto);
            direccion=itemView.findViewById(R.id.textview_direccion);
            cardView=itemView.findViewById(R.id.card_view_ventas);
        }
    }

}