def totalEarnings = 0.0

println "How many jobs have you had?"
def numberOfJobsInput = System.in.newReader().readLine()

if (numberOfJobsInput == null || !numberOfJobsInput.isNumber()) {
    println "Invalid input. Please enter a valid number of jobs."
    return
}

def numberOfJobs = numberOfJobsInput.toInteger()

for (int i = 1; i <= numberOfJobs; i++) {
    println "Enter the number of months you worked at job $i:"
    def monthsWorkedInput = System.in.newReader().readLine()

    if (monthsWorkedInput == null || !monthsWorkedInput.isNumber()) {
        println "Invalid input. Please enter a valid number of months."
        return
    }

    def monthsWorked = monthsWorkedInput.toInteger()

    println "Enter your monthly salary for job $i:"
    def monthlySalaryInput = System.in.newReader().readLine()

    if (monthlySalaryInput == null || !monthlySalaryInput.isNumber()) {
        println "Invalid input. Please enter a valid monthly salary."
        return
    }

    def monthlySalary = monthlySalaryInput.toDouble()
    totalEarnings += (monthlySalary * monthsWorked)
}

println "Do you have any other income amounts to add? (yes/no)"
def hasOtherIncome = System.in.newReader().readLine().toLowerCase()

while (hasOtherIncome == 'yes') {
    println "Enter the other income amount:"
    def otherIncomeInput = System.in.newReader().readLine()

    if (otherIncomeInput == null || !otherIncomeInput.isNumber()) {
        println "Invalid input. Please enter a valid income amount."
        return
    }

    def otherIncome = otherIncomeInput.toDouble()
    totalEarnings += otherIncome

    println "Do you have any more other income amounts to add? (yes/no)"
    hasOtherIncome = System.in.newReader().readLine().toLowerCase()
}

println "The total amount of money you have earned through your life is: ${totalEarnings} RON"

