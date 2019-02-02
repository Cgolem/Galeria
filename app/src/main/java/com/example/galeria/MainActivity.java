package com.example.galeria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgVista;
    Button btnGaleria;
    Button btnCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciamos los botones e imageView
        imgVista = (ImageView) findViewById(R.id.imgVista);
        btnGaleria = (Button) findViewById(R.id.btnGaleria);
        btnCamara = (Button) findViewById(R.id.btnCamara);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //Si el codigo de respuesta es correcto y el codigo resultado es OK, indica que se realizó con éxito
        if( requestCode == 100 && resultCode == RESULT_OK){

            //Asignamos a nuestro imageView la imagen seleccionada en la galaería
            imgVista.setImageURI(data.getData());
        }

        if( requestCode == 200 && requestCode == RESULT_OK) {
            //Asignamos a nuestro imageView la imagen capturada con la cámara
            imgVista.setImageBitmap((Bitmap) data.getExtras().get("data"));
        }
    }

    /*
    * @description: Método con la funcionalidad del botón para abrir la galería
    * */
    public void abrirGaleria(View view) {
        //Generamos nuestro Intent y asignamos una acción. También indicamos que usaremos el contenido de la
        //Memoria externa
        Intent galeriaExterna = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        //Inicializamos lnuestro Intent y asignamos el código de respuesta.
        startActivityForResult(galeriaExterna, 100);
    }

    /*
     * @description: Método con la funcionalidad del botón para abrir la cámara
     * */
    public void abrirCamara(View view) {
        //Generamos nuestro intent e indicamos que queremos usar la acción de captura de imagen
        Intent capturaImagen = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(capturaImagen, 200);
    }
}
