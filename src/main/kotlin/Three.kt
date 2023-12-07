import java.io.BufferedReader
import java.io.File

class Three {


    val filePath = "src/Data/DayThree.txt"
    var data: String? = null
    var lines = mutableListOf<String>()
    var sum = 0

    public fun part1(){
        val bufferdReader: BufferedReader = File(filePath).bufferedReader()
        data = bufferdReader.use { it.readText() }
        lines = data!!.split("\r\n").toMutableList()
        lines.forEach {
            sum += it.getValue(lines.indexOf(it))
        }
        println("Ergebnis $sum")

    }

    private fun String.getValue(index: Int):Int{
        var result = 0
        var mapOfNumbers = mutableMapOf<String, Array<Int>>()
        var tmp = ""

        var inlineIndex = 0
        for (i in this){
            if(i.isDigit()) {
                tmp += i
                 if(inlineIndex == 0)
                     inlineIndex = indexOf(i)
            }
            else{
                if(tmp != ""){
                    mapOfNumbers[tmp] = arrayOf(inlineIndex, this.indexOf(tmp[tmp.length-1]), index)
                    tmp = ""
                    inlineIndex = 0
                }
            }
        }

        for(entry in mapOfNumbers){
            println(entry.key)
            if(checkSourunding(entry.value)){
                result += entry.key.toInt()
                println("${entry.key.toInt()} übernommen")
            }


        }


        return result
    }

    private fun checkSourunding(infos: Array<Int>):Boolean{
        var result = false

        if(infos[2] > 0)
            if(checkAbove(infos[0], infos[1], infos[2]))
                return true




        return result

    }

    private fun checkAbove(start:Int, end:Int, index:Int):Boolean{
        var result = false

        var counter = start
        while(counter <= end){
            if(lines.get(index-1)[counter].isValid())
                return true
            counter++
        }


        return result
    }

    private fun Char.isValid():Boolean{

        if(!this.isDigit() && !this.equals('.'))
            return true
        else
            return false
    }




}