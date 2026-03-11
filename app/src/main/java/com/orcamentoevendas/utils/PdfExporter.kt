package com.orcamentoevendas.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import android.graphics.pdf.PdfDocument
import com.orcamentoevendas.data.local.entity.OrcamentoEntity
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object PdfExporter {

    lateinit var context: Context  // Inicialize no Application ou passe como parâmetro

    fun gerarECompartilharPdf(orcamento: OrcamentoEntity) {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = android.graphics.Paint()
        paint.textSize = 12f

        canvas.drawText("Orçamento de Materiais", 10f, 25f, paint)
        canvas.drawText("Peso total: ${"%.2f".format(orcamento.pesoTotal)} kg", 10f, 50f, paint)
        canvas.drawText("Valor total: R$ ${"%.2f".format(orcamento.valorTotal)}", 10f, 75f, paint)
        val dataFormatada = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(orcamento.data))
        canvas.drawText("Data: $dataFormatada", 10f, 100f, paint)

        document.finishPage(page)

        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "orcamento_${orcamento.id}.pdf")
        document.writeTo(FileOutputStream(file))
        document.close()

        val uri: Uri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Compartilhar PDF"))
    }
}