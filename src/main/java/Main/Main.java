package Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        String fileRepository = "src/main/resources/";
        int rowNum = readRowNum();
        File file = new File(fileRepository + "PeopleDataSet.pdf");
        String[] names = new String[rowNum],
                patronymics = new String[rowNum],
                surnames = new String[rowNum],
                dates = new String[rowNum],
                ages = new String[rowNum],
                index = new String[rowNum],
                countries = new String[rowNum],
                houses = new String[rowNum],
                flats = new String[rowNum],
                sexes = new String[rowNum],
                hometowns = new String[rowNum],
                regions = new String[rowNum],
                cities = new String[rowNum],
                streets = new String[rowNum];
        int daysInYear = 365;
        int hundredYearsInDays = 100 * daysInYear;
        ArrayList<String> femaleNamesList = getArrayFromFile(fileRepository + "WomenFirstNames.txt");
        ArrayList<String> maleNamesList = getArrayFromFile(fileRepository + "MenFirstNames.txt");
        ArrayList<String> surnamesList = getArrayFromFile(fileRepository + "Surnames.txt");
        ArrayList<String> femalePatronymicList = getArrayFromFile(fileRepository + "WomenPatronymic.txt");
        ArrayList<String> malePatronymicList = getArrayFromFile(fileRepository + "MenPatronymic.txt");
        ArrayList<String> streetsList = getArrayFromFile(fileRepository + "Streets.txt");
        ArrayList<String> citiesList = getArrayFromFile(fileRepository + "Cities.txt");
        ArrayList<String> regionsList = getArrayFromFile(fileRepository + "Regions.txt");
        ArrayList<String> hometownsList = getArrayFromFile(fileRepository + "Cities.txt");

        // создаем массивы со случайными данными из файлов
        for (int i = 0; i < rowNum; i++) {
            int randomCountOfDays = randomNumber(hundredYearsInDays);
            dates[i] = getData(randomCountOfDays);
            ages[i] = getAge(randomCountOfDays, daysInYear);
            index[i] = Integer.toString(100000 + randomNumber(899999));
            countries[i] = "Россия";
            houses[i] = Integer.toString(randomNumber(200));
            flats[i] = Integer.toString(randomNumber(555));
            if (randomNumber(2) == 1) {
                sexes[i] = "MУЖ";
                names[i] = maleNamesList.get(randomNumber(maleNamesList.size() - 1));
                surnames[i] = surnamesList.get(randomNumber(surnamesList.size() - 1));
                patronymics[i] = malePatronymicList.get(randomNumber(malePatronymicList.size() - 1));
            } else {
                sexes[i] = "ЖЕН";
                names[i] = femaleNamesList.get(randomNumber(femaleNamesList.size() - 1));
                surnames[i] = surnamesList.get(randomNumber(surnamesList.size() - 1)) + "а";
                patronymics[i] = femalePatronymicList.get(randomNumber(femalePatronymicList.size()));
            }
            hometowns = getRandomArray(hometownsList, rowNum);
            regions = getRandomArray(regionsList, rowNum);
            cities = getRandomArray(citiesList, rowNum);
            streets = getRandomArray(streetsList, rowNum);
        }
        //создаем pdf документ
        Document myPDFDoc = new Document(PageSize.A2.rotate(),
                40f,   // left
                40f,   // right
                40f,  // top
                40f); // down


        //1) создаем таблицу
            Table myTable = new Table(14); // 14 колонок
            myTable.setPadding(5f);
            myTable.setSpacing(1f);
            myTable.setWidth(100f);

        //2) создаем строку заголовков для таблицы
            ArrayList<String> headerTable = new ArrayList<>();
            headerTable.add("Фамилия");
            headerTable.add("Имя");
            headerTable.add("Отчество");
            headerTable.add("Возраст");
            headerTable.add("Пол");
            headerTable.add("Дата рождения");
            headerTable.add("Место рождения");
            headerTable.add("Индекс");
            headerTable.add("Страна");
            headerTable.add("Область");
            headerTable.add("Город");
            headerTable.add("Улица");
            headerTable.add("Дом");
            headerTable.add("Квартира");

            headerTable.forEach(e -> {
                com.lowagie.text.Cell current = new com.lowagie.text.Cell(new Phrase(e));
                current.setHeader(true);
                current.setBackgroundColor(new java.awt.Color(255, 221, 45));  //Tinkoff color =)
                myTable.addCell(current);
            });

        // 3) заполняем таблицу строками из массивов данных
            LinkedHashMap<Integer, List<String>> listRows = new LinkedHashMap<>();
            for (int j = 0; j < rowNum; j++) {
                listRows.put(j+1, Arrays.asList(surnames[j], names[j], patronymics[j], ages[j], sexes[j], dates[j], hometowns[j], index[j], countries[j], regions[j], cities[j], streets[j], houses[j], flats[j]));
            }
            listRows.forEach((indexNew,userDetailRow) -> {
                String currentSurname = userDetailRow.get(0);
                String currentName = userDetailRow.get(1);
                String currentPatronymics = userDetailRow.get(2);
                String currentAge = userDetailRow.get(3);
                String currentSex = userDetailRow.get(4);
                String currentData = userDetailRow.get(5);
                String currentHomeTown = userDetailRow.get(6);
                String currentIndex = userDetailRow.get(7);
                String currentCountry = userDetailRow.get(8);
                String currentRegions = userDetailRow.get(9);
                String currentCity = userDetailRow.get(10);
                String currentStreet = userDetailRow.get(11);
                String currentHouse = userDetailRow.get(12);
                String currentFlat = userDetailRow.get(13);

                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentSurname)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentName)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentPatronymics)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentAge)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentSex)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentData)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentHomeTown)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentIndex)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentCountry)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentRegions)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentCity)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentStreet)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentHouse)));
                myTable.addCell(new com.lowagie.text.Cell(new Phrase(currentFlat)));
            });


            try {

                FileOutputStream pdfOutputFile = new FileOutputStream(file);

                final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, pdfOutputFile);
                pdfWriter.setPageEvent(new PdfPageEventHelper() {
                    @Override
                    public void onEndPage(PdfWriter writer, Document document) {
                    }
                });
                System.out.println("Файл создан. Путь: " + file.getAbsolutePath());

                myPDFDoc.open();  // Открываем документ

                /* Добавляем метаданные в наш пдф файл */
                myPDFDoc.addTitle("This is a local people dataset");
                myPDFDoc.addSubject("This is a homework with creating PDF");
                myPDFDoc.addKeywords("Java, CreatePDF, PeopleDataSet");
                myPDFDoc.addCreator("Main");
                myPDFDoc.addAuthor("ShuklinaAF");


                // Добавляем таблицу в документ
                myPDFDoc.add(myTable);

                myPDFDoc.close();
                pdfWriter.close();
            } catch (IOException de) {
                System.err.println(de.getMessage());
            }
    }


    private static int readRowNum() {
        Scanner in = new Scanner(System.in);
        int rowNum = 0;
        System.out.print("Введите число от 1 до 30: ");
        try {
            rowNum = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка! Неверные входные данные! Вероятнее всего, Вы ввели не число");
        }
        if (rowNum < 1 || rowNum > 30) {
            System.out.println("Ваше число не соответствует условию." +
                    "\nБудет автоматически сгенерирован файл из одной строки.");
            rowNum = 1;
        }
        in.close();
        return rowNum;
    }

    private static int randomNumber(int max) {
        return (int) (Math.random() * max);
    }

    private static ArrayList<String> getArrayFromFile(String path) throws Exception {
        ArrayList<String> array = new ArrayList<>();
        FileReader cityFile = new FileReader(path);
        Scanner scan = new Scanner(cityFile);
        while (scan.hasNextLine()) {
            array.add(scan.nextLine());
        }
        cityFile.close();
        return array;
    }

    private static String getData(int days) {
        Calendar date = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date.add(Calendar.DAY_OF_MONTH, -days);
        return dateFormat.format(date.getTime());
    }

    private static String getAge(int daysSinceBirth, int daysInYear) {
        return Integer.toString(daysSinceBirth / daysInYear);
    }

    private static String[] getRandomArray(ArrayList<String> originArray, int newArrayLength) {
        String[] newArray = new String[newArrayLength];
        for (int i = 0; i < newArrayLength; i++) {
            newArray[i] = originArray.get(randomNumber(originArray.size() - 1));
        }
        return newArray;
    }

}