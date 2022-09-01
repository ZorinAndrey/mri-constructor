package ru.azor.mri_constructor.utils;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.azor.mri_constructor.Application;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentUtil {
    public static void createDocument() {
        Map<String, String> change = new HashMap<>();
        change.put("[Ось]", Application.INSTANCE.getStudy().getAxis().getProtocol());
        change.put("[Лордоз]", Application.INSTANCE.getStudy().getLordosis().getProtocol());
        change.put("[Сагиттальный размер]", Application.INSTANCE.getStudy().getSagittalSize().getProtocol());
        change.put("[СПОНДИЛОДИСЦИТ]", Application.INSTANCE.getStudy().getSpondylodiscitis().getProtocol());
        change.put("[Тела позвонков]", String.join(", ", Application.INSTANCE.getStudy().getVertebrae().getProtocol()));
        change.put("[VC]", String.join(", ", Application.INSTANCE.getStudy().getVertebrae().getConclusion()));
        change.put("[AC]", Application.INSTANCE.getStudy().getAxis().getConclusion());
        change.put("[LC]", Application.INSTANCE.getStudy().getLordosis().getConclusion());
        change.put("[SaC]", Application.INSTANCE.getStudy().getSagittalSize().getConclusion());
        change.put("[SpC]", Application.INSTANCE.getStudy().getSpondylodiscitis().getConclusion());
        changeDocx(change);
    }

    private static void changeDocx(Map<String, String> change) {
        try {
            Path source = Paths.get("./template.docx");
            Path temp = Paths.get("src/main/resources/temp/", "result.docx");
            Path target = Paths.get("./", "result.docx");
            Files.copy(source, temp, StandardCopyOption.REPLACE_EXISTING);

            try (XWPFDocument doc = new XWPFDocument(OPCPackage.open(temp.toString()))) {
                for (XWPFParagraph p : doc.getParagraphs()) {
                    List<XWPFRun> runs = p.getRuns();
                    if (runs != null) {
                        for (XWPFRun r : runs) {
                            String text = r.getText(0);
                            for (Map.Entry<String, String> entry : change.entrySet()) {
                                if (text != null && text.contains(entry.getKey())) {
                                    text = text.replace(entry.getKey(), entry.getValue());
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
                doc.write(new FileOutputStream(target.toString()));
            }
            Files.deleteIfExists(temp);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
