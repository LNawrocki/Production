package pl.ln.methods;

import pl.ln.entity.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreatePosFromArray {

    private static final Pattern IS_POS_ROW = Pattern.compile("^[0-9]+");
    private static final Pattern IS_last_ROW = Pattern.compile("[0-9]+\\.[0-9]{2}\\.[0-9]{4}");

    public static List<Pos> createPosFromArray(String[][] array) {
        int posRowNumber = 0;

        List<Pos> posList = new ArrayList<>();
        int posId = 0;
        Pos pos = null;
        boolean readPos = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i].length > 0) {
                if (array[i][0].equals("Pos.") && array[i][0].length() == 4) {
                    readPos = true;
                }
                if (array[i][0].contains("Order:")) {
                    readPos = false;
                }

                if (readPos && IS_POS_ROW.matcher(array[i][0]).find() && !IS_last_ROW.matcher(array[i][0]).matches()) {
                    posId++;
                    pos = new Pos();
                    pos.setPos(array[i][0]);
                    pos.setArticleCode(array[i][1]);
                    pos.setPcs(Integer.parseInt(array[i][2].isBlank() ? "0" : array[i][2]));
                    pos.setUnit(array[i][3]);
                    pos.setAdditionalInformation(array[i][4]);
                    posList.add(pos);
                }
            }
        }
        return posList;
    }
}
