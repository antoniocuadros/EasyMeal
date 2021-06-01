package com.acl.easymeal

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class ConversorImagen {

    @TypeConverter
    public fun bitmapToByteArray(imagenBitmap:Bitmap):ByteArray{
        val streamSalida:ByteArrayOutputStream
        streamSalida = ByteArrayOutputStream()

        imagenBitmap.compress(Bitmap.CompressFormat.PNG, 100, streamSalida)
        return streamSalida.toByteArray()
    }

    @TypeConverter
    public fun byteArrayToBitmap(imagenByteArray: ByteArray):Bitmap{
        val bitmap:Bitmap = BitmapFactory.decodeByteArray(imagenByteArray, 0, imagenByteArray.size)
        return bitmap
    }

    public fun stringToID(nombre:String, context:Context):Int{
        return context.resources.getIdentifier(nombre, "drawable", "com.acl.easymeal")
    }
}