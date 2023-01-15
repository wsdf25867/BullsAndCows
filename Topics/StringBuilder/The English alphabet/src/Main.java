class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code here
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb;
    }
}
