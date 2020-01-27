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
        addZero(other);
        int acarreo = 0;
        int numActual;
        StringBuilder resultado = new StringBuilder();
        for (int i = this.numeroString.length()-1; i >= 0 ; i--) {
            numActual = ((this.numeroString.charAt(i) -48) + (acarreo) + (other.numeroString.charAt(i)-48));
            acarreo = numActual/10;
            if (numActual >= 10){
                numActual = numActual-10;
                resultado.insert(0, numActual);
            }else{
                resultado.insert(0, numActual);
            }
        }
        if (acarreo == 1) resultado.insert(0,1);
        return new BigNumber(removeZero(resultado.toString()));
    }

    // Resta
    BigNumber sub(BigNumber other) {
        addZero(other);
        int acarreo = 0, numActual;

        if (this.compareTo(other) == -1){
            String temp = other.numeroString;
            other.numeroString = this.numeroString;
            this.numeroString = temp;
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = this.numeroString.length()-1; i >= 0 ; i--) {

            if ((this.numeroString.charAt(i) -48) < ((acarreo) + (other.numeroString.charAt(i)-48))){
                numActual = ((this.numeroString.charAt(i) -38) - ((acarreo) + (other.numeroString.charAt(i)-48)));
                acarreo = 1;
            } else{
                numActual = ((this.numeroString.charAt(i) -48) - ((acarreo) + (other.numeroString.charAt(i)-48)));
                acarreo = numActual/10;
            }
            if (numActual >= 10){
                numActual = numActual-10;
                resultado.insert(0, numActual);
            }else if (numActual < 0){
                acarreo = 1;
                numActual = numActual* -1;
                resultado.insert(0, numActual);
            }else{
                resultado.insert(0, numActual);
            }
        }
        if (acarreo == 1) resultado.insert(0,1);
        return new BigNumber(resultado.toString());
    }

    // Multiplica
    BigNumber mult(BigNumber other) {

        int cerosDerecha = 0;
        BigNumber resultado = new BigNumber("");

        for (int i = this.numeroString.length()-1; i >= 0; i--) {
            BigNumber sumaActual = new BigNumber("");
            int acarreo = 0;
            for (int j = other.numeroString.length()-1; j >= 0; j--) {

                int num1 = Integer.parseInt(String.valueOf(this.numeroString.charAt(i)));
                int num2 = Integer.parseInt(String.valueOf(other.numeroString.charAt(j)));
                int numActual =  (num1 * num2 + acarreo);
                acarreo = 0;

                if (numActual >9){
                    acarreo = numActual / 10;
                    numActual = numActual%10;
                }
                sumaActual.numeroString = numActual + sumaActual.numeroString;
            }
            for (int j = cerosDerecha; j > 0; j--) sumaActual.numeroString += "0";

            if (acarreo > 0) sumaActual.numeroString = acarreo + sumaActual.numeroString;

            cerosDerecha++;
            resultado = resultado.add(sumaActual);
        }
        return resultado;

        /*
        other.numeroString =  other.removeZero(other.numeroString);
       this.numeroString =  this.removeZero(this.numeroString);

        System.out.println(this.numeroString);
        System.out.println(other.numeroString);
        BigNumber resultado = new BigNumber(this.numeroString);
        BigNumber resultadoFinal = new BigNumber("");
        int cerosDerecha = -1;
        for (int i = other.numeroString.length()-1; i > 0 ; i--) {
            cerosDerecha++;
            int vecesMult = other.numeroString.charAt(1) -48;
            for (int j = vecesMult; j < 0; j--) {
                resultado = this.add(resultado);
            }
            resultado.numeroString += cerosDerecha;
            resultadoFinal = resultadoFinal.add(resultado);
        }*/
       // return  resultadoFinal;
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

        for (int i = 1; i < n ; i++) {
            mult(this);
        }

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
            if (this.numeroString.charAt(i) > other.numeroString.charAt(i)) return 1;
            if (this.numeroString.charAt(i) < other.numeroString.charAt(i)) return -1;
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
            addZero(other);
            return this.numeroString.equals(((BigNumber) other).numeroString);
        }
        return false;
    }

    public void addZero(Object otherObject) {
        BigNumber other = (BigNumber) otherObject;
        int numDiferencia = 0;
        if (other.numeroString.length() != this.numeroString.length()) {

            if (other.numeroString.length() > this.numeroString.length()) {
                numDiferencia = other.numeroString.length() - this.numeroString.length();
                for (int i = numDiferencia; i > 0; i--) {
                    this.numeroString = "0" + this.numeroString;
                }
            } else {
                numDiferencia = this.numeroString.length() - other.numeroString.length();
                for (int i = numDiferencia; i > 0; i--) {
                    other.numeroString = "0" + other.numeroString;
                }
            }
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
        BigNumber b4 = new BigNumber("02");
        BigNumber b2 = new BigNumber("102");
        BigNumber b3 = new BigNumber("00000102");

        System.out.println(b1.mult(b4));
        System.out.println(b3.removeZero(b3.numeroString));
    }
}
