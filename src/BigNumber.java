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
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return this.numeroString;
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {
        if (other instanceof BigNumber){

            BigNumber numeroOther = (BigNumber) other;
            int numDiferencia = 0;

            if (this == other) return true;

            if (numeroOther.numeroString.length() != this.numeroString.length()) {
                if (numeroOther.numeroString.length() > this.numeroString.length()) {
                    numDiferencia = numeroOther.numeroString.length() - this.numeroString.length();
                    this.addZero(numDiferencia);
                    }
                } else {
                   numDiferencia = this.numeroString.length() - numeroOther.numeroString.length() ;
                   numeroOther.addZero(numDiferencia);
                }
                for (int i = 1; i <= this.numeroString.length(); i++) {
                    if (this.numeroString.charAt(i) != numeroOther.numeroString.charAt(i) ) return false;
                }
            return true;
        }
        return false;
    }

    public void addZero(int totalZeros){

        for (int i = totalZeros; i > 0 ; i--) {
            this.numeroString = "0" + this.numeroString;
        }
    }

    public void sameLenght(BigNumber b){

        /*
        * Prueba de añadir 0 al de menor tamaño
        int longitudString1 = this.numeroString.length();
        int longitudString2 = b.numeroString.length();

        if (longitudString1 > longitudString2){
            for (int i = longitudString2 ; i <= longitudString1 ; i++) {
                b.numeroString =  "0" + b.numeroString;
            }
        }

        if (longitudString1 < longitudString2){
            for (int i = longitudString1 ; i <= longitudString2 ; i++) {
                this.numeroString =  "0" + this.numeroString;
            }
        }*/


    }

    public String removeZeroOnLeftSide(BigNumber number){
        for (int i = 0; i < number.numeroString.length() ; i++) {
            char character = number.numeroString.charAt(0);
            if (character == '0') {
                StringBuilder sb = new StringBuilder(number.numeroString);
                sb.deleteCharAt(0);
                number.numeroString = sb.toString();
                System.out.println(number.numeroString);
            } else {
                break;
            }
        }
        return numeroString;
    }



    /*
    * Main para pruebas personales
    * */
    public static void main(String[] args) {


    }
}
