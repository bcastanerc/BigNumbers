public class BigNumber {

    private String numeroString;

    public String getNumeroString() {
        return numeroString;
    }

    public void setNumeroString(String numeroString) {
        this.numeroString = numeroString;
    }

    // Constructor 1
    public BigNumber(String s) {
        this.numeroString = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {
        this.numeroString = b.numeroString;
    }

    // Suma
    BigNumber add(BigNumber other) {
        return null;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        return null;
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        return null;
    }

    // Divideix
    BigNumber div(BigNumber other) {
        return null;
    }

    // Arrel quadrada
    BigNumber sqrt() {
        return null;
    }

    // Potència
    BigNumber power(int n) {
        return null;
    }

    // Factorial
    BigNumber factorial() {
        return null;
    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {
        return null;
    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1
    // si el primer és menor i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {

        this.numeroString = this.removeZero(this.numeroString);
        other.numeroString = other.removeZero(other.numeroString);
        if (this.equals(other)) return 0;
        if (this.numeroString.length() < other.numeroString.length()) return -1;
        if (this.numeroString.length() > other.numeroString.length()) return 1;
        for (int i = 0; i < this.numeroString.length(); i++) {
            char Num1 = this.numeroString.charAt(i);
            char Num2 = other.numeroString.charAt(i);
            if (Num1 > Num2) return 1;
            if (Num1 < Num2) return -1;
        }
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return this.numeroString;
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        if (this == other) return true;
        if (other instanceof BigNumber){
            BigNumber numeroOther = (BigNumber) other;
            int numDiferencia = 0;

            if (numeroOther.numeroString.length() != this.numeroString.length()) {
                if (numeroOther.numeroString.length() > this.numeroString.length()) {
                    numDiferencia = numeroOther.numeroString.length() - this.numeroString.length();
                    this.addZero(numDiferencia);
                    }
                } else {
                   numDiferencia = this.numeroString.length() - numeroOther.numeroString.length() ;
                   numeroOther.addZero(numDiferencia);
                }
            return this.numeroString.equals(numeroOther.numeroString);
        }
        return false;
    }

    public void addZero(int totalZeros){

        for (int i = totalZeros; i > 0 ; i--) {
            this.numeroString = "0" + this.numeroString;
        }
    }


    public String removeZero(String str)
    {
        // Contamos el total de 0 que hay
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') i++;

        StringBuilder sb = new StringBuilder(str);
        sb.replace(0, i, "");
        return sb.toString();
    }

    /*
    * Main para pruebas personales
    * */
    public static void main(String[] args) {
        BigNumber b1 = new BigNumber("2");
        BigNumber b2 = new BigNumber("102");
        BigNumber b3 = new BigNumber("00000102");

        if(b1.equals(b2)){
            System.out.println("son iguales");
        }else{
            System.out.println("diferentes");
        }

        if(b1.equals(b3)){
            System.out.println("son iguales");
        }else{
            System.out.println("diferentes");
        }

        if(b2.equals(b3)){
            System.out.println("son iguales");
        }else{
            System.out.println("diferentes");
        }

        String numero = b3.removeZero(b3.numeroString);
        System.out.println(numero);
    }
}
