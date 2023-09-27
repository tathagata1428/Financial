import java.util.Scanner

// Initialize the Scanner
scanner = new Scanner(System.in)

def readItems(String type, boolean isLiability = false, boolean isIncome = false) {
    def items = []
    while (true) {
        println "Enter the name of the ${type} (or 'done' to finish): "
        def name = scanner.nextLine()
        if (name.toLowerCase().equals('done')) break

        if (isIncome) {
            println "Enter the monthly income amount for ${name} in RON: "
        } else if (isLiability) {
            println "Enter the monthly payment amount for ${name} in RON: "
        } else {
            println "Enter the amount for ${name} in RON: "
        }

        def amountStr = scanner.nextLine()
        def amount = Double.parseDouble(amountStr)

        if (isLiability) {
            println "Enter the number of months remaining for ${name}: "
            def monthsStr = scanner.nextLine()
            def months = Integer.parseInt(monthsStr)
            amount *= months
        }
        
        items.add([name, amount])
    }
    return items
}

def assets = readItems("asset")
def liabilities = readItems("liability", true)
def incomes = readItems("income", false, true)

def calculateTotal(items) {
    return items.collect { it[1] }.sum()
}

def totalAssets = calculateTotal(assets)
def totalLiabilities = calculateTotal(liabilities)
def totalIncomes = calculateTotal(incomes)
def equity = (totalAssets + totalIncomes) - totalLiabilities

def toMarkdown(items, title) {
    def markdown = "## $title\n\n"
    markdown += "| Item | Amount (RON) |\n"
    markdown += "|------|---------------|\n"
    items.each { markdown += "| ${it[0]} | RON${String.format('%,.2f', it[1])} |\n" }
    return markdown
}

def assetsMarkdown = toMarkdown(assets, "Assets")
def liabilitiesMarkdown = toMarkdown(liabilities, "Liabilities")
def incomesMarkdown = toMarkdown(incomes, "Incomes")

def balanceSheetMarkdown = """
# Balance Sheet

$assetsMarkdown

### Total Assets: RON${String.format('%,.2f', totalAssets)}

$liabilitiesMarkdown

### Total Liabilities: RON${String.format('%,.2f', totalLiabilities)}

$incomesMarkdown

### Total Incomes: RON${String.format('%,.2f', totalIncomes)}

## Equity: RON${String.format('%,.2f', equity)}
"""

println balanceSheetMarkdown

def file = new File("BalanceSheet.md")
file.text = balanceSheetMarkdown
