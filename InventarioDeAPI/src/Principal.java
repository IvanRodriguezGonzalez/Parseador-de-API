public class Principal {
    private final String PATH = ".//";

    public static void main(String[] args) {
        new Principal().ejecutar();
    }

    private void ejecutar() {
        DirectorioRaml directorio = new DirectorioRaml(PATH);
        directorio.procesarFicherosRaml();
     }

}
