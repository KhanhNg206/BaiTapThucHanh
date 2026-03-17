package Exerise;

import enity.Country;
import enity.DetailTrans;
import enity.Name;
import enity.Translations;

import java.lang.reflect.Array;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Country c = new Country(
                1,
                Arrays.asList("VN",
                        "Socialist Republic of Vietnam",
                        "Cộng hòa Xã hội chủ nghĩa Việt Nam",
                        "Viet Nam"),
                331212,
                Arrays.asList("KHM","CHN","LAO"),
                Arrays.asList("84"),
                "Hanoi",
                "VN",
                "VIE",
                Arrays.asList("VND"),
                "Vietnamese",
                false,
                Arrays.asList(16.16666666,107.83333333),
                new Name("VietNam","Socialist Republic of Vietnam"),
                "Asia",
                "South-Eastern Asia",
                new Translations(
                        new DetailTrans("vn","République socialiste du Vietnam"),
                        new DetailTrans("Vn","République socialiste du Vietnam")
                )
        );

        if(c.getCapital().equals("Hanoi")){
            System.out.println("pass");
            WriterJson.Writer(c,"D:\\USBDaMat\\LapTrinhPhanTanVoiCongNgheJava\\TK_Json_Model\\src\\main\\java\\data\\country.json");
        }else{
            System.out.println("no pass");
        }

    }
}
