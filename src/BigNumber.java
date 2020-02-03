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

    /**
     * Esta función se encarga de hacer la suma de dos BigNumbers.
     * @param other objeto de tipo BigNumber con el cual haremos las operaciones, a parte de this.
     * @return devuelve un BigNumber con el resultado de la suma.
     */
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
            }
            resultado.insert(0, numActual);
        }
        if (acarreo == 1) resultado.insert(0,1);
        return new BigNumber(removeZero(resultado.toString()));
    }

    /**
     * Esta función realiza la resta, tenemos que asegurarnos de que el mayor esté "arriba".
     * @param other objeto de tipo BigNumber con el cual haremos las operaciones, a parte de this.
     * @return devuelve un BigNumber con el resultado de la resta.
     */
    BigNumber sub(BigNumber other) {

        if (!this.validBigNumber(other)) return null;
        addZero(other);

        int acarreo = 0;
        int numActual;

        /* Si this es inferior a other se cambian this por other */
        if (this.compareTo(other) == -1) {
            String temp = other.numeroString;
            other.numeroString = this.numeroString;
            this.numeroString = temp;
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = this.numeroString.length() - 1; i >= 0; i--) {

            if ((this.numeroString.charAt(i) - 48) < ((acarreo) + (other.numeroString.charAt(i) - 48))) {
                numActual = ((this.numeroString.charAt(i) - 38) - ((acarreo) + (other.numeroString.charAt(i) - 48)));
                acarreo = 1;
            } else {
                numActual = ((this.numeroString.charAt(i) - 48) - ((acarreo) + (other.numeroString.charAt(i) - 48)));
                acarreo = numActual / 10;
            }
            if (numActual >= 10) {
                numActual = numActual - 10;
                resultado.insert(0, numActual);
            } else if (numActual < 0) {
                acarreo = 1;
                numActual = numActual * -1;
                resultado.insert(0, numActual);
            } else {
                resultado.insert(0, numActual);
            }
        }
        if (acarreo == 1) resultado.insert(0, 1);
        return new BigNumber(resultado.toString());
    }

    /**
     * Esta función se encarga de hacer la multiplicación de dos BigNumbers.
     * @param other objeto de tipo BigNumber con el cual haremos las operaciones, a parte de this.
     * @return devuelve un BigNumber con el resultado de la multiplicación.
     */
    BigNumber mult(BigNumber other) {

        /* Si uno de los dos numores por los que se ha de multiplicar es 0 devuelve 0 */
        if ((this.compareTo(new BigNumber("0")) == 0) || (other.compareTo(new BigNumber("0"))) == 0){
            return new BigNumber("0");
        }

        if (!this.validBigNumber(other)) return null;

        int cerosDerecha = 0;
        BigNumber resultado = new BigNumber("");


        for (int i = this.numeroString.length() - 1; i >= 0; i--) {
            BigNumber sumaActual = new BigNumber("");
            int acarreo = 0;
            for (int j = other.numeroString.length() - 1; j >= 0; j--) {
                int numActual = ((this.numeroString.charAt(i)-48) * (other.numeroString.charAt(j) -48) + acarreo);
                acarreo = 0;

                /* Si numActual es mayor que 10 hay que restarle 10 y añadir 1 al acarreo */
                if (numActual > 9) {
                    acarreo = numActual / 10;
                    numActual = numActual % 10;
                }
                sumaActual.numeroString = numActual + sumaActual.numeroString;
            }
            for (int j = cerosDerecha; j > 0; j--) sumaActual.numeroString += "0";
            if (acarreo > 0) sumaActual.numeroString = acarreo + sumaActual.numeroString;
            cerosDerecha++;
            resultado = resultado.add(sumaActual);
        }
        return resultado;
    }

    /**
     * Esta funcion se encarga de hacer la división de un numero entre otro, para hacer la división se hará como se hace a papel.
     * Simplemente usaremos la función de resta para ir restandole al numero actual.
     * @param other objeto de tipo BigNumber con el cual haremos las operaciones, a parte de this.
     * @return devuelve un objeto de tipo bignumber con el resultado.
     */
    BigNumber div(BigNumber other) {

        if (!this.validBigNumber(other)) return null;

        BigNumber result = new BigNumber("");
        BigNumber actual = new BigNumber("");

        for (int i = 0; i < this.numeroString.length(); i++) {

            actual.numeroString += this.numeroString.charAt(i);
            int contador = 0;
            /* Restamos a actual other hasta que sean iguales o other sea mayor */
            while(actual.compareTo(other) != -1){
                    actual = actual.sub(other);
                    contador++;
            }
            result.numeroString += contador;
        }
        return result;
    }

    /**
     * Esta función es la encargada de calcular la raiz cuadrada de un BigNumber para esto tendremos que dividir el BigNumber
     * crear un array para almacenarlo y luego hacer las operaciones debidas.
     * @return devuelve un Objeto de tipo BigNumber con la raiz cuadrada
     */
    BigNumber sqrt() {

        if (!validBigNumber(this)) return null;


        BigNumber resto = new BigNumber("");
        BigNumber resultado = new BigNumber("");

        /* Si el numero es impar le añadimos un cero */
        if ((this.numeroString.length()%2) != 0){
            this.numeroString = "0" + this.numeroString;
        }
        String[] digitArray = new String [this.numeroString.length()/2];

        /* Introducimos los digitos por pares en el array */
        for (int i = 0, j = 0; i < this.numeroString.length()/2 ; i++, j+=2) {
            String num = "" + (this.numeroString.charAt(j)) + (this.numeroString.charAt(j+1));
            digitArray[i] = num;
        }

        for (int i = 0; i <digitArray.length; i++) {

            BigNumber actual = new BigNumber("");
            resto.numeroString += digitArray[i];

            for (int j = 0; j <= 9; j++) {

                StringBuilder sb = new StringBuilder();
                sb = new StringBuilder(new BigNumber(resultado).mult(new BigNumber("2")).toString());
                sb.append(j + 1);
                actual = new BigNumber(sb.toString());

                if (actual.mult(new BigNumber(Integer.toString(j+1))).compareTo(resto) == 1  ){

                    sb = new StringBuilder(new BigNumber(resultado).mult(new BigNumber("2")).toString());
                    sb.append(j);
                    actual = new BigNumber(sb.toString());
                    actual = actual.mult(new BigNumber(Integer.toString(j)));
                    resto = resto.sub(actual);
                    resultado.numeroString += Integer.toString(j);
                    break;

                }
            }
        }
        return resultado;
    }

    /**
     * Esta función eleva un BigNumber a una potencia, lo cual significa que se multiplicara por si mismo tantas veces
     * como diga la potencia.
     * @param n és la potencia, el numero que dice cuantas veces se va a multiplicar por si mismo.
     * @return devuelve el resultado en un BigNumber
     */
    BigNumber power(int n) {

        if (!validBigNumber(this)) return null;
        /* Si la potencia a la que se eleva es 0 */
        if (n == 0) return new BigNumber("1");

        BigNumber resultado = new BigNumber(this.numeroString);
        for (int i = n; i > 1; i--) resultado = this.mult(resultado);
        return resultado;
    }

    /**
     * Esta función calcula el factorial de un numero multiplicandose por todos sus numeros inferiores.
     * @return devuelve un BigNumber con el resultado
     */
    BigNumber factorial() {
        if (!validBigNumber(this)) return null;
        BigNumber resultado = new BigNumber("1");
        BigNumber numActual = new BigNumber("1");

        /* Vamos incrementando numActual hasta this */
        while (numActual.compareTo(this) != 0) {
            numActual = (numActual.add(new BigNumber("1")));
            resultado = numActual.mult(resultado);
        }
        return resultado;
    }

    /**
     * Para calcular el mcd usamos la division ya que con el resto de esta e intencaviando los valores podemos calcular el mcd.
     * @param other objeto de tipo BigNumber con el cual haremos las operaciones, a parte de this.
     * @return devuelve un BigNumber con el resultado.
     */
    BigNumber mcd(BigNumber other) {

        if (!this.validBigNumber(other)) return null;

        if (this.compareTo(other) == -1) {
            String temp = other.numeroString;
            other.numeroString = this.numeroString;
            this.numeroString = temp;
        }

        BigNumber cero = new BigNumber("0");

        if (this.equals(cero)) return other;
        if (other.equals(cero)) return this;

        return other.mcd(calculateRemainder(other));
    }

    /**
     * Esta funcion calcula el resto de una division, esta nos sirve de apoyo para calcular el mcd.
     * @param other objeto de tipo BigNumber con el cual haremos las operaciones, a parte de this.
     * @return devuelve un BigNumber con el resto.
     */
    public BigNumber calculateRemainder(BigNumber other){
        /* Multiplicamos el coeficiente por el divisor y luego se lo restamos al dividendo, así obtenemos el resto */
        return this.sub(this.div(other).mult(other));
    }


    /**
     * Esta función compara dos BigNumber para averiguar cual es el mayor de los dos o si son iguales.
     * @param other objeto de tipo BigNumber con el cual haremos las comparaciones, a parte de this.
     * @return Devuelve un int, si this es mayor que other devuelve 1, si this y other son iguales devuelve 0,
     * sí other es mayor que this devuelve -1.
     */
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

    /**
     * Esta funcion pasa a String el objeto desde el que se le llama.
     * @return Devuelve un string con el atributo del  objeto.
     */
    @Override
    public String toString() {
        return this.numeroString;
    }

    /**
     * Esta función compara dos BigNumber para ver si son iguales.
     * @param other objeto de tipo Object con el cual haremos las comparaciones, a parte de this.
     * @return devuelve un boolean, true si son iguales false si son diferences
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof BigNumber){
            /* Añadimos ceros  al que sea necesario*/
            this.addZero(other);
            return this.numeroString.equals(((BigNumber) other).numeroString);
        }
        return false;
    }

    /**
     * Estq función se encarga deañadir ceros a la izquierda al string más pequeño para que los dos sean del mismo tamaño.
     * @param otherObject  objeto de tipo Object con el cual haremos las comparaciones, a parte de this.
     */
    public void addZero(Object otherObject) {
        BigNumber other = (BigNumber) otherObject;
        int numDiferencia = 0;
        if (other.numeroString.length() != this.numeroString.length()) {
            /* Añadimos ceros a thisr*/
            if (other.numeroString.length() > this.numeroString.length()) {
                numDiferencia = other.numeroString.length() - this.numeroString.length();
                for (int i = numDiferencia; i > 0; i--) {
                    this.numeroString = "0" + this.numeroString;
                }
            } else {
                /* Añadimos ceros a other*/
                numDiferencia = this.numeroString.length() - other.numeroString.length();
                for (int i = numDiferencia; i > 0; i--) {
                    other.numeroString = "0" + other.numeroString;
                }
            }
        }
    }

    /**
     * Esta función se encarga de eliminar los ceros de la izquierda en una string
     * @param str string a la cual le quitaremos los ceros.
     * @return devuelve un string sin ceros a la izquierda.
     */
    public String removeZero(String str) {
        int i = 0;
        /* Cuenta el total de ceros a la izquierda */
        while (i < str.length() && str.charAt(i) == '0') i++;
        StringBuilder sb = new StringBuilder(str);
        sb.replace(0, i, "");
        return sb.toString();
    }

    /**
     * Esta función comprueba que el atributo numeroString de los objetos este compuesto por numeros del 0-9 y tengan al
     * menos un caracter, si no se cumple dará false.
     * @param other objeto de tipo BigNumber con el cual haremos las comparaciones, a parte de this.
     * @return tipo boolean
     */
    public boolean validBigNumber(BigNumber other){

        /* Comprueba si la longitud del string es igual a 0 */
        if ((this.numeroString.length() == 0) || (other.numeroString.length() == 0)) return false;

        /* Comprueba que todos los sean numeros */
        for (int i = 0; i < this.numeroString.length(); i++) {
            if ((this.numeroString.charAt(i) < 48) || (this.numeroString.charAt(i) > 48+9)) return false;
        }
        for (int i = 0; i <other.numeroString.length() ; i++) {
            if ((other.numeroString.charAt(i) < 48) || (other.numeroString.charAt(i) > 48+9)) return false;
        }
        return true;
    }
}
