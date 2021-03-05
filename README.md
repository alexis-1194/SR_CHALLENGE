SR CHALLENGE: Caso Selectores Web

Requerimientos:

IDE: Eclipse, intellij idea, VS Code, etc.
Lenguajes: Java
Drivers: Selenium y ChromeDriver

Desarrollo:

1. Crear nuevo proyecto e incluir drivers especificados en los requerimentos.
2. Crear una clase prueba para la solución (AppRater.java) 

Implementando logica de la solución:

1. Creamos el método para la carga del navegador

        public static void launchBrowser() {
              String baseUrl = "https://apprater.net/add/";
              System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
              driver = new ChromeDriver();
              driver.get(baseUrl);
              driver.manage().window().maximize();
        }
  
2. implementamos la logica principal con 2 métodos (sentText y sendAfterValidate).
 
3. Adicionalmente podemos implementar un método para verificar la carga completa de la pagina
  
        public static boolean waitForLoad(WebDriver driver) {
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            };
            WebDriverWait wait = new WebDriverWait(driver, 30);
            return wait.until(pageLoadCondition);
        }
  
 4. Verificar las pruebas con los ejemplos del caso, utilizando cssSelector, xpath y Name.
 
    launchBrowser();

        if (waitForLoad(driver)) {
            sendText("Your Name", "name", "user-submitted-name", "Lucas Ocampo");
            sendText("Email Address", "css", "#usp_form > div:nth-child(3) > div:nth-child(2) > fieldset > input",
                    "Lucas@Ocampo.com");
            sendText("Product Title", "css", "#usp_form > fieldset.usp-title > input",
                    "Prisma - AI that runs your phots into artwork in seconds");
            sendText("Product URL", "xpath", "//*[@id=\"usp_form\"]/div[4]/fieldset/input",
                    "https://apprater.net/add/Prisma");
            sendText("Product Tags", "xpath", "//*[@id=\"usp_form\"]/div[5]/div[1]/fieldset/input",
                    "game, Android, IA");
        }
  




