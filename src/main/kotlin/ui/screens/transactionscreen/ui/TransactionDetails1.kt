package ui.screens.transactionscreen.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import models.transactions.Transaction
import org.jetbrains.skia.Font
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Typeface
import ui.components.containers.ScrollableColumnContainer
import utils.toDate
import java.awt.*
import java.awt.print.PageFormat
import java.awt.print.Printable
import java.awt.print.PrinterJob
import kotlin.math.roundToInt

@Composable
fun TransactionDetils(transaction: Transaction = myTransaction) {

    val scrollState = rememberScrollState()

    ScrollableColumnContainer(
        modifier = Modifier.fillMaxSize(),
        scrollState = scrollState,
    ) {
        Canvas(
            modifier = Modifier
                .width(canvasWidth)
                .height(canvasHeight)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Magenta,
                            Color.Red,
                            Color.Blue,
                            Color.Cyan
                        )
                    ),
                    alpha = 0.2f
                ),
            onDraw = {
                this.drawIntoCanvas {

                    it.drawString(
                        s = "INVOICE",
                        x = (size.width / 2).roundToInt() - 190,
                        y = (size.height / 2).roundToInt() + 50,
                        fontSize = 100,
                        fontAlpha = 15,
                        fontStyle = FontStyle.BOLD
                    )


                    it.drawString(
                        s = "XYZ Motor Repairing",
                        x = nameOffset.x,
                        y = nameOffset.y,
                        fontSize = sizeOfStoreName,
                        fontAlpha = alphaOfTitle,
                        fontStyle = FontStyle.BOLD,
                    )
                    it.drawString(
                        s = "Gadhawa - 6,",
                        x = address1Offset.x,
                        y = address1Offset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Gadhawa, Dang, Lumbini",
                        x = address2Offset.x,
                        y = address2Offset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Phone# 082-540670",
                        x = phoneOffset.x,
                        y = phoneOffset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Email# example@xyzgaraze.com",
                        x = emailOffset.x,
                        y = emailOffset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )
                    it.drawString(
                        s = "Invoice#  ${transaction.id}",
                        x = invoiceOffset.x,
                        y = invoiceOffset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.ITALIC
                    )
                    it.drawString(
                        s = "Date#  ${transaction.transactionDate.toDate()}",
                        x = dateOffset.x,
                        y = dateOffset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.ITALIC
                    )

                    it.drawString(
                        s = "To",
                        x = toOffset.x,
                        y = toOffset.y,
                        fontSize = sizeOfCustomerName,
                        fontAlpha = alphaOfOtherTexts
                    )

                    it.drawString(
                        s = "Amit Chaudhary",
                        x = customerNameOffset.x,
                        y = customerNameOffset.y,
                        fontSize = sizeOfCustomerName,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Gadhawa -07, Kanchhi Gaun",
                        x = customerAddressOffset.x,
                        y = customerAddressOffset.y,
                        fontSize = sizeOfCustomerAddress,
                        fontAlpha = alphaOfOtherTexts
                    )

                    it.drawString(
                        s = "Particulars",
                        x = particularOffset.x,
                        y = particularOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Qty",
                        x = qtyOffset.x,
                        y = qtyOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Unit Cost",
                        x = unitCostOffset.x,
                        y = unitCostOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Amount",
                        x = amountOffset.x,
                        y = amountOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Gross Total",
                        x = grossTotalLabelOffset.x,
                        y = grossTotalLabelOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Discount",
                        x = discountLabelOffset.x,
                        y = discountLabelOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Net Total",
                        x = netTotalLabelOffset.x,
                        y = netTotalLabelOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Paid Amount",
                        x = paidAmountLabelOffset.x,
                        y = paidAmountLabelOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Payment Due",
                        x = paymentDueLabelOffset.x,
                        y = paymentDueLabelOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Served By:",
                        x = servedByLabelOffset.x,
                        y = servedByLabelOffset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                    )

                    it.drawString(
                        s = "Verified By:",
                        x = verifiedByLabelOffset.x,
                        y = verifiedByLabelOffset.y,
                        fontSize = sizeOfOtherTexts,
                        fontAlpha = alphaOfOtherTexts,
                    )

                    // Variable Value
                    it.drawString(
                        s = "Rs. ${transaction.grossAmount}",
                        x = grossTotalOffset.x,
                        y = grossTotalOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Rs. ${transaction.grossAmount - transaction.netAmount}",
                        x = discountOffset.x,
                        y = discountOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Rs. ${transaction.netAmount}",
                        x = netTotalOffset.x,
                        y = netTotalOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Rs. ${transaction.netAmount - transaction.paymentDue}",
                        x = paidAmountOffset.x,
                        y = paidAmountOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    it.drawString(
                        s = "Rs. ${transaction.paymentDue}",
                        x = paymentDueOffset.x,
                        y = paymentDueOffset.y,
                        fontSize = sizeOfTableTitle,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD
                    )

                    var y = InnerHeaderLine.startOffset.y.roundToInt() + 30
                    if (transaction.typeOfService.isNotEmpty()) {
                        it.drawString(
                            s = "Services",
                            x = startingOffset.x + 30,
                            y = y,
                            fontSize = sizeOfOtherTexts,
                            fontAlpha = 255,
                            fontStyle = FontStyle.BOLD_ITALIC
                        )

                        y += sizeOfTableContent + 20

                        transaction.typeOfService.forEach { s ->
                            it.drawString(
                                s = s,
                                x = startingOffset.x + 30,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            it.drawString(
                                s = "1",
                                x = VerticalLine1.startOffset.x.roundToInt() + 35,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            it.drawString(
                                s = "Rs. 500",
                                x = VerticalLine2.startOffset.x.roundToInt() + 25,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            it.drawString(
                                s = "Rs. 500",
                                x = VerticalLine3.startOffset.x.roundToInt() + 35,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            y += sizeOfTableContent + 15
                        }
                        y += 25
                    }

                    if (transaction.addOnProducts.isNotEmpty()) {
                        it.drawString(
                            s = "Products",
                            x = startingOffset.x + 30,
                            y = y,
                            fontSize = sizeOfOtherTexts,
                            fontAlpha = 255,
                            fontStyle = FontStyle.BOLD_ITALIC
                        )

                        y += sizeOfTableContent + 20

                        transaction.addOnProducts.forEach { s ->
                            it.drawString(
                                s = s,
                                x = startingOffset.x + 30,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            it.drawString(
                                s = "1",
                                x = VerticalLine1.startOffset.x.roundToInt() + 35,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            it.drawString(
                                s = "Rs. 500",
                                x = VerticalLine2.startOffset.x.roundToInt() + 25,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            it.drawString(
                                s = "Rs. 500",
                                x = VerticalLine3.startOffset.x.roundToInt() + 35,
                                y = y,
                                fontSize = sizeOfTableContent,
                                fontAlpha = alphaOfOtherTexts,
                                fontStyle = FontStyle.ITALIC
                            )

                            y += sizeOfTableContent + 15
                        }
                    }

                    it.drawString(
                        s = "BRC",
                        x = servedByOffset.x,
                        y = servedByOffset.y,
                        fontSize = sizeOfOtherTexts + 2,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD_ITALIC
                    )

                    it.drawString(
                        s = "itheamc",
                        x = verifiedByOffset.x,
                        y = verifiedByOffset.y,
                        fontSize = sizeOfOtherTexts + 2,
                        fontAlpha = alphaOfOtherTexts,
                        fontStyle = FontStyle.BOLD_ITALIC
                    )


                }

                drawRect(
                    color = Color(0xff616161),
                    topLeft = rect.topLeft,
                    size = rect.size,
                    style = Stroke(
                        width = 1.2f,
                        cap = StrokeCap.Square,
                        join = StrokeJoin.Round
                    )
                )

                drawLine(
                    start = InnerHeaderLine.startOffset,
                    end = InnerHeaderLine.endOffset
                )

                drawLine(
                    start = VerticalLine1.startOffset,
                    end = VerticalLine1.endOffset
                )

                drawLine(
                    start = VerticalLine2.startOffset,
                    end = VerticalLine2.endOffset
                )

                drawLine(
                    start = VerticalLine3.startOffset,
                    end = VerticalLine3.endOffset
                )

                drawLine(
                    start = HorizontalLine1.startOffset,
                    end = HorizontalLine1.endOffset
                )

                drawLine(
                    start = HorizontalLine2.startOffset,
                    end = HorizontalLine2.endOffset
                )

                drawLine(
                    start = HorizontalLine3.startOffset,
                    end = HorizontalLine3.endOffset
                )

                drawLine(
                    start = HorizontalLine4.startOffset,
                    end = HorizontalLine4.endOffset
                )

            }
        )
    }
}


/**
 * -----------------------------
 * Demo Transaction
 */
private val myTransaction = Transaction(
    id = "DEC15202113258PM",
    transactionDate = 1639554478106,
    customerId = "NtYEkKbdTd",
    vehicle = "Tipper",
    vehicleId = "gYQnzbN4Bs",
//    typeOfService = listOf("Y4WzHAXKfr", "Y5anWQbPXe", "lGlyzH2SPf"),
    typeOfService = listOf(
        "Engine Repairing",
        "Break Pad Replacement",
        "Brake Maintenance",
//        "Greasing",
//        "Air Filter Replacement",
//        "Wheel Balancing"
    ),
//    addOnProducts = listOf("JKGkjGBjHJjg", "JHFghkDghdFG"),
    addOnProducts = listOf("Brake Ladder", "Grease 20 K.G.", "Break Leader Pad", "Bearing", "Other Minor tools"),
    grossAmount = 4500,
    netAmount = 2955,
    discount = 1.0f,
    offer = "H807We",
    paymentDue = 955,
    paymentMethod = "Cash",
    servicedBy = listOf("U0caYB", "pgJ3J9"),
    transactionAddedBy = "itheamc"
)


fun Canvas.drawString(
    s: String,
    x: Int,
    y: Int,
    fontSize: Int,
    fontColor: Color = Color(0xff616161),
    fontAlpha: Int,
    fontStyle: FontStyle = FontStyle.NORMAL
) {
    nativeCanvas.drawString(
        s = s,
        x = x.toFloat(),
        y = y.toFloat(),
        paint = Paint().apply {
            color = fontColor
            alpha = fontAlpha / 255f
            isAntiAlias = true
        }.asFrameworkPaint(),
        font = Font(
            typeface = Typeface.makeFromName(name = "itheamc", style = fontStyle),
            size = fontSize.toFloat()
        )
    )
}

// Draw Line
fun DrawScope.drawLine(start: Offset, end: Offset) {
    drawLine(
        color = Color(0xff616161),
        start = start,
        end = end,
        strokeWidth = 1.2f
    )
}


private val canvasWidth = 840.dp
private val canvasHeight = 1100.dp
private val sizeOfStoreName = 40
private val alphaOfTitle = 255
private val sizeOfOtherTexts = 18
private val sizeOfTableTitle = 18
private val sizeOfTableContent = 14
private val alphaOfOtherTexts = 220
private val sizeOfCustomerName = 24
private val sizeOfCustomerAddress = 16
private val startingOffset = IntOffset(36, 80)
private val nameOffset = startingOffset
private val address1Offset = startingOffset.copy(
    y = startingOffset.y + sizeOfStoreName - 20
)
private val address2Offset = address1Offset.copy(
    y = address1Offset.y + sizeOfOtherTexts + 2
)
private val phoneOffset = address2Offset.copy(
    y = address2Offset.y + sizeOfOtherTexts + 2
)
private val emailOffset = phoneOffset.copy(
    y = phoneOffset.y + sizeOfOtherTexts + 2
)
private val invoiceOffset = emailOffset.copy(
    x = ((canvasWidth.value / 3f) * 2f).roundToInt(),
    y = address2Offset.y + sizeOfOtherTexts + 2
)
private val dateOffset = invoiceOffset.copy(
    y = invoiceOffset.y + sizeOfOtherTexts + 2
)
private val toOffset = dateOffset.copy(
    x = startingOffset.x,
    y = emailOffset.y + sizeOfOtherTexts + 80
)
private val customerNameOffset = toOffset.copy(
    y = toOffset.y + sizeOfCustomerName + 2
)
private val customerAddressOffset = customerNameOffset.copy(
    y = customerNameOffset.y + sizeOfCustomerName
)

// Table Drawing Offset
private val top = (customerAddressOffset.y + sizeOfCustomerAddress + 10).toFloat()
private val bottom = top + ((sizeOfTableContent + 2) * 40).toFloat()
//private val outerRect = Rect(
//    left = startingOffset.x.toFloat(),
//    right = canvasWidth.value - startingOffset.x,
//    top = top,
//    bottom = bottom
//)

private val rect: androidx.compose.ui.geometry.Rect = Rect(
    topLeft = Offset(
        x = startingOffset.x.toFloat(),
        y = top
    ),
    bottomRight = Offset(
        x = canvasWidth.value - startingOffset.x,
        y = bottom
    )
)

//private object UpperHeaderLine {
//    val startOffset: IntOffset = IntOffset(startingOffset.x, top.roundToInt())
//    val endOffset: IntOffset = IntOffset(outerRect.right.roundToInt(), top.roundToInt())
//}

private object InnerHeaderLine {
    val startOffset: Offset = Offset(startingOffset.x.toFloat(), top + sizeOfTableTitle + 20)
    val endOffset: Offset = Offset(rect.right, top + sizeOfTableTitle + 20)
}

// Horizontal Lines
private object HorizontalLine1 {
    val startOffset: Offset = Offset(rect.left, rect.bottom - (sizeOfOtherTexts * 8))
    val endOffset: Offset = Offset(rect.right, rect.bottom - (sizeOfOtherTexts * 8))
}

private object HorizontalLine2 {
    val startOffset: Offset = Offset(rect.left, rect.bottom - (sizeOfOtherTexts * 5))
    val endOffset: Offset = Offset(rect.right, rect.bottom - (sizeOfOtherTexts * 5))
}

private object HorizontalLine3 {
    val startOffset: Offset = Offset(rect.left, rect.bottom - (sizeOfOtherTexts * 2))
    val endOffset: Offset = Offset(rect.right, rect.bottom - (sizeOfOtherTexts * 2))
}

private object HorizontalLine4 {
    val startOffset: Offset = Offset(rect.left, rect.bottom - 4)
    val endOffset: Offset = Offset(rect.right, rect.bottom - 4)
}


// Vertical Lines
private object VerticalLine1 {
    val startOffset: Offset = Offset((canvasWidth.value / 2) + 100f, rect.top)
    val endOffset: Offset = Offset((canvasWidth.value / 2) + 100f, HorizontalLine1.startOffset.y)
}

private object VerticalLine2 {
    val startOffset: Offset = Offset(VerticalLine1.startOffset.x + 75f, rect.top)
    val endOffset: Offset = Offset(VerticalLine1.startOffset.x + 75f, HorizontalLine1.startOffset.y)
}

private object VerticalLine3 {
    val startOffset: Offset = Offset(VerticalLine2.startOffset.x + 90f, rect.top)
    val endOffset: Offset = Offset(VerticalLine2.startOffset.x + 90f, rect.bottom)
}

private val particularOffset = IntOffset(
    x = startingOffset.x + 150,
    y = top.roundToInt() + 25
)

private val qtyOffset = particularOffset.copy(
    x = VerticalLine1.startOffset.x.roundToInt() + 23
)

private val unitCostOffset = qtyOffset.copy(
    x = VerticalLine2.startOffset.x.roundToInt() + 7
)
private val amountOffset = unitCostOffset.copy(
    x = VerticalLine3.startOffset.x.roundToInt() + 25
)


private val grossTotalLabelOffset = IntOffset(
    x = startingOffset.x + 10,
    y = HorizontalLine1.startOffset.y.roundToInt() + 24
)

private val discountLabelOffset = grossTotalLabelOffset.copy(
    y = grossTotalLabelOffset.y + sizeOfOtherTexts + 5
)

private val netTotalLabelOffset = discountLabelOffset.copy(
    y = HorizontalLine2.startOffset.y.roundToInt() + 24
)
private val paidAmountLabelOffset = netTotalLabelOffset.copy(
    y = netTotalLabelOffset.y + sizeOfOtherTexts + 5
)
private val paymentDueLabelOffset = paidAmountLabelOffset.copy(
    y = HorizontalLine3.startOffset.y.roundToInt() + 24
)


private val grossTotalOffset = IntOffset(
    x = VerticalLine3.startOffset.x.roundToInt() + 25,
    y = HorizontalLine1.startOffset.y.roundToInt() + 24
)

private val discountOffset = grossTotalOffset.copy(
    y = grossTotalOffset.y + sizeOfOtherTexts + 5
)

private val netTotalOffset = discountOffset.copy(
    y = HorizontalLine2.startOffset.y.roundToInt() + 24
)
private val paidAmountOffset = netTotalOffset.copy(
    y = netTotalOffset.y + sizeOfOtherTexts + 5
)
private val paymentDueOffset = paidAmountOffset.copy(
    y = HorizontalLine3.startOffset.y.roundToInt() + 24
)

private val servedByLabelOffset = IntOffset(
    x = rect.left.roundToInt(),
    y = (canvasHeight.value - 36).roundToInt()
)

private val verifiedByLabelOffset = servedByLabelOffset.copy(
    x = VerticalLine2.startOffset.x.roundToInt()
)

private val servedByOffset = IntOffset(
    x = servedByLabelOffset.x + 90,
    y = (canvasHeight.value - 36).roundToInt()
)

private val verifiedByOffset = servedByOffset.copy(
    x = VerticalLine3.startOffset.x.roundToInt() + 5
)