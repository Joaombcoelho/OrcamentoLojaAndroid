package com.orcamentoevendas.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import com.orcamentoevendas.data.local.entity.ResultadoEntity
import java.io.File
import java.io.FileOutputStream
import java.text.NumberFormat
import java.util.*

object PdfExporter {

    fun gerarPdf(context: Context, historico: List<ResultadoEntity>): File {

        val document = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = document.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()

        val formatoMoeda = NumberFormat.getCurrencyInstance(Locale("pt","BR"))

        var y = 40

        // TÍTULO
        paint.textSize = 18f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

        canvas.drawText("Orçamento de Materiais", 40f, y.toFloat(), paint)

        y += 30

        // DATA
        paint.textSize = 10f
        paint.typeface = Typeface.DEFAULT

        canvas.drawText(
            "Gerado em: ${Date()}",
            40f,
            y.toFloat(),
            paint
        )

        y += 40

        var pesoTotal = 0.0
        var valorTotal = 0.0

        paint.textSize = 12f

        historico.forEach {

            canvas.drawText(
                "Peso: %.3f kg".format(it.pesoTotal),
                20f,
                y.toFloat(),
                paint
            )

            y += 18

            canvas.drawText(
                "Valor: ${formatoMoeda.format(it.valorTotal)}",
                20f,
                y.toFloat(),
                paint
            )

            y += 18

            canvas.drawText(
                "Data: ${it.data}",
                20f,
                y.toFloat(),
                paint
            )

            y += 28

            pesoTotal += it.pesoTotal
            valorTotal += it.valorTotal
        }

        y += 20

        // LINHA
        canvas.drawLine(20f, y.toFloat(), 280f, y.toFloat(), paint)

        y += 30

        // TOTAL
        paint.textSize = 14f
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

        canvas.drawText(
            "Peso Total: %.3f kg".format(pesoTotal),
            20f,
            y.toFloat(),
            paint
        )

        y += 22

        canvas.drawText(
            "Valor Total: ${formatoMoeda.format(valorTotal)}",
            20f,
            y.toFloat(),
            paint
        )

        document.finishPage(page)

        val file = File(context.getExternalFilesDir(null), "orcamento.pdf")

        document.writeTo(FileOutputStream(file))
        document.close()

        return file
    }
}