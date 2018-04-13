public class Principal {
    private final String PATH = "C:\\Users\\u8332\\Desktop\\Raml\\";

    public static void main(String[] args) {
        new Principal().ejecutar();
    }

    private void ejecutar() {
        DirectorioRaml directorio = new DirectorioRaml(PATH);
        directorio.procesarFicherosRaml();
     }

}
