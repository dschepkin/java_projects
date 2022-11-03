package com.dschepkin.javaCore.string;

public class _String {
    public static void main(String[] args) {
        /**
         * isBlank, isEmpty
         */
        String string = " ";
        System.out.println(string.isBlank()); //true
        System.out.println(string.isEmpty()); //false
        /**
         * Cast
         */
        int value = 4;
        String string2 = String.valueOf(value);
        /**
         * String format
         */
        String strFormat = "String Format test";
        String strResult = String.format("%s", strFormat);
        System.out.println(strResult);

        /**
         * Написать ф-цию разбивающую строку на равные части по n символов и сохраняем в массив
         */
        String str04 = "The cat walk on garden";
        int symbolOffset = 3;
        //......
        //Определяем размер проходов и оно же будет равно размеру массива
        int cntIteration = str04.length() / symbolOffset;
        //Если не делится нацело, то увеличиваем кол-во итераций
        if (str04.length() % symbolOffset != 0) {
            cntIteration++;
        }
        //где будем хранить массив строк
        String[] resultArr = new String[cntIteration];
        //......
        //еще вариант как можно было определить размер массива - округлить в большую сторону
        int arraySize = (int) Math.ceil(str04.length() / (double) symbolOffset);
        //
        int startIndex = 0;
        int endIndex = 3;

        for (int i = 0; i < cntIteration; i++) {
//            System.out.println(str04.substring(startIndex,endIndex));
            resultArr[i] = str04.substring(startIndex,endIndex);
            startIndex += 3;
            endIndex += 3;

            if(endIndex > str04.length()) {
                endIndex = str04.length();
            }
        }

        for(String str : resultArr) {
            System.out.println(str);
        }

        /**
         * Написать ф-ций считающую кол-во точек, восклицательных и вопросительных знаков в строке
         * Решение: Удалим исковый символы и посчитаем разность длинн строк
         */

        String str05 = "Vasia go to school.!?";
        String resultStr05 = str05.replace(".", "")
                .replace("!", "")
                .replace("?", "");
        System.out.println(str05.length() - resultStr05.length());
    }
}
