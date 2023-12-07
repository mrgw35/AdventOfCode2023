import java.io.BufferedReader
import java.io.File

class Two {

    private val filePath = "src/Data/DayTwo.txt"

    private val reds = 12
    private val greens = 13
    private val blues = 14

    fun getPossibleGames(){

        var sum = 0
        val bufferdReader: BufferedReader = File(filePath).bufferedReader()
        val data = bufferdReader.use { it.readText() }
        var games = data.split("\n")
        games.forEach {

            if (it.isPossible()){
                val regex = Regex("\\d+")
                val id = regex.find(it)
                sum += id?.value?.toInt()!!
                println(id.value)

            }
        }
        println("Ergebnis $sum")

    }

    private fun String.isPossible(): Boolean{
        var result = false
        val regex = Regex("\\s*[:;]\\s*")
        val turns: List<String> = regex.split(this)
        for (turn in turns){
            if(indexOf(turn) == 0)
                continue
            result = turn.checkColor()

            if(!result)
                return false
        }

        return result
    }

    private fun String.checkColor(): Boolean{
        var result = false
        var regex = Regex(",")
        var extractNumbers = Regex("\\d+")
        var cubes = regex.split(this)
        cubes.forEach {
            if(it.contains("blue")){
                var amount = extractNumbers.find(it)?.value?.toInt()
                result = inRange("blue", amount)
            }
            if(it.contains("red")){
                var amount = extractNumbers.find(it)?.value?.toInt()
                result = inRange("red", amount)
            }
            if(it.contains("green")){
                var amount = extractNumbers.find(it)?.value?.toInt()
                result = inRange("green", amount)
            }
            if(!result)
                return result
        }


        return result
    }

    private fun inRange(colour: String, amount: Int?): Boolean{
        if(amount != null){
            when(colour){
                "blue" -> {
                    return amount <= blues
                }
                "green" -> {return amount <= greens}
                "red" -> {return amount <= reds}
            }
            return false
        }
        return false
    }


    fun getPowerOfLeastNecessaryCubes(){
        var sum = 0
        val bufferdReader: BufferedReader = File(filePath).bufferedReader()
        val data = bufferdReader.use { it.readText() }
        val games = data.split("\n")
        games.forEach {
            sum += it.getPowerOfMinimum()
        }

        println("Ergebnis $sum")

    }

    fun String.getPowerOfMinimum():Int{
        var power = 0

        val red = this.getAll("red").getMinimum()
        val green = this.getAll("green").getMinimum()
        val blue = this.getAll("blue").getMinimum()

        println("Rot: $red\nBlau: $blue\nGrün: $green")

        return (red*green*blue)
    }

    fun String.getAll(key: String):List<MatchResult>{
        val regex = Regex("(\\d+)\\s+$key")
        val results = regex.findAll(this).toList()

        return results
    }
    fun List<MatchResult>.getMinimum():Int{
        var min = 0
        var max = mutableListOf<Int>()
        this.forEach {
            max.add(it.groupValues[1].toInt())
        }



        return max.max()
    }




}