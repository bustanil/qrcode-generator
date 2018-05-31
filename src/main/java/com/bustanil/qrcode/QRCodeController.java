package com.bustanil.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/qr")
public class QRCodeController {

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public void getImageAsByteArray(@RequestParam String value, @RequestParam(required = false) String filename,
            @RequestParam(required = false) Integer size, HttpServletResponse response) throws IOException, WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(value, BarcodeFormat.QR_CODE, size, size);
        response.setHeader("Content-Disposition", "inline; filename=\"" + (filename == null ? "qr.png" : filename) + "\"");
        response.setContentType("image/png");
        MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
    }

}
