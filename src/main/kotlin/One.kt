import java.io.BufferedReader
import java.io.File

class One {


    var filePath = "src/data.txt"
    var filePathTwo = "src/Data/One/dataTwo.txt"
    public fun getValue(){

        val bufferdReader: BufferedReader = File(filePath).bufferedReader()
        val data = bufferdReader.use { it.readText() }
        var seperated = data.split('\n')
        var sum = 0;
        seperated.forEach {
            var char = it.getNumber();
            if(char.isNotEmpty())
                sum += char.toInt()


        }
        println(sum)
    }

    private fun String.getNumber(): String {

        var numbers = this.filter { it.isDigit() }
        var tmp = ""
        if(numbers.isNotEmpty()) {
            tmp = "${numbers.first()}"
            tmp += "${numbers.last()}"
        }
        return tmp
    }

    fun getValueSecondStar(){
        val bufferdReader: BufferedReader = File(filePathTwo).bufferedReader()
        val data = bufferdReader.use { it.readText() }
        var splitData = data.split("\n")
        var sum = 0;
        splitData.forEach {
            //var char = it.getNumberVTwo();
            val char = it.better()

                sum += char.toInt()



        }
        println(sum)

    }

    private fun String.getNumberVTwo(): String{
        var tmp = this.backToInt()

        return tmp.getNumber()
    }

    private fun String.backToInt(): String{
        var tmpString = ""
        if(this.contains("one"))
            tmpString += 1
        if(this.contains("two"))
            tmpString += 2
        if(this.contains("three"))
            tmpString += 3
        if(this.contains("four"))
            tmpString += 4
        if(this.contains("five"))
            tmpString += 5
        if(this.contains("six"))
            tmpString += 6
        if(this.contains("seven"))
            tmpString += 7
        if(this.contains("eight")|| this.contains("ight"))
            tmpString += 8
        if(this.contains("nine")|| this.contains("ine"))
            tmpString += 9
        if(tmpString == ""){
            tmpString = this
        }
        return tmpString

    }



    private fun String.better():String{
        var x = this.replace("one" , "oonee")
        x = x.replace("two", "ttwoo")
        x = x.replace("three", "tthreee")
        x = x.replace("four", "ffourr")
        x = x.replace("seven", "ssevenn")
        x = x.replace("six", "ssixx")
        x = x.replace("five", "ffivee")
        x = x.replace("ten", "ttenn")
        x = x.replace("nine", "nninee")
        x = x.replace("eight", "eeightt")
        val regex = Regex("(one|two|three|four|five|six|seven|eight|nine|ten|\\d)")


        val matches = regex.findAll(x)

        //matches.forEach { println(it.value) }

        var tmp = matches.first().value.backToInt() +  matches.last().value.backToInt()

        var first = tmp[0]
        var last = tmp[tmp.length -1]


        tmp = "$first$last"

        println("$tmp  -- $this")

       // println(tmp)
        return tmp

    }
}