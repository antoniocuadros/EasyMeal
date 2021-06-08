package com.acl.easymeal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.lang.Exception

/*
    Esta clase aporta los métodos necesarios para convertir imágenes de bytes a bitmap y viceversa.
    Esto será utilizado para almacenar y recuperar los datos de la base de datos. Room hace uso de
    los typeConverters para hacer esto de forma automática.
 */
class ConversorImagen {

    /*
        Este método transforma una imagen proporcionada como un array de bytes en un objeto de tipo
        bitmap que es como las manejamos en la aplicación.
     */
    @TypeConverter
    public fun bitmapToByteArray(imagenBitmap:Bitmap?):ByteArray?{
            val streamSalida:ByteArrayOutputStream
            streamSalida = ByteArrayOutputStream()

            imagenBitmap?.compress(Bitmap.CompressFormat.PNG, 100, streamSalida)
            return streamSalida.toByteArray()
    }

    /*
        Este método transforma una imagen proporcionada como un objeto de tipo bitmap a un array de bytes
        que es como se almacenan en la base de datos.
     */
    @TypeConverter
    public fun byteArrayToBitmap(imagenByteArray: ByteArray?):Bitmap?{
        try {
            val bitmap:Bitmap = BitmapFactory.decodeByteArray(imagenByteArray!!, 0, imagenByteArray?.size!!)
            return bitmap
        }catch (e:Exception){
            return null
        }


    }

    /*
        Este método dado un nombre de imagen, obtiene el id de la imagen almacenada en la carpeta drawable.
     */
    public fun stringToID(nombre:String, context:Context):Int{
        return context.resources.getIdentifier(nombre, "drawable", "com.acl.easymeal")
    }
}