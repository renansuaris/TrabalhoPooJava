package enums;

public enum Color {

	/*
	 *  Enum para implementar cores no programa principal
	 *  Objetivo de deixar a interface mais amigável com o usuário
	 *  Onde aprendi a customizar: 
	 *  https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	 */
	
	//Color end string, color reset
    RESET("\033[0m"),

    // Regular Colors. Normal color, no bold, background color etc.
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED  
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE
    MAGENTA("\033[0;35m"),  // MAGENTA
    CYAN("\033[0;36m"),     // CYAN 
    WHITE("\033[0;37m"),    // WHITE

    // Bold High Intensity
    BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
    RED_BOLD_BRIGHT("\033[1;91m"),      // RED
    GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
    YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
    BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
    MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
    CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
    WHITE_BOLD_BRIGHT("\033[1;97m");    // WHITE

    private final String code;

	Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
