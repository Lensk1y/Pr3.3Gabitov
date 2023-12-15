import kotlin.random.Random

fun main() {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    println("Введите ключ:")
    val key = readLine().toString()
    println("Введите сообщение:")
    val message = readLine().toString()

    val cipherTable = generateCipherTable(alphabet)

    println("Original message: $message")
    println("Key: $key")

    val encryptedMessage = encryptVigenere(message, key, cipherTable)
    println("Encrypted message: $encryptedMessage")

    printCipherTable(cipherTable)
}

fun generateCipherTable(alphabet: String): List<String> {
    val cipherTable = mutableListOf<String>()
    val shuffledAlphabet = alphabet.toList().shuffled()

    for (i in alphabet.indices) {
        val shiftedAlphabet = shuffledAlphabet.subList(i, alphabet.length) + shuffledAlphabet.subList(0, i)
        cipherTable.add(shiftedAlphabet.joinToString(""))
    }

    return cipherTable
}

fun encryptVigenere(message: String, key: String, cipherTable: List<String>): String {
    var encryptedMessage = ""

    for (i in message.indices) {
        val messageChar = message[i]
        val keyChar = key[i % key.length]
        val rowIndex = cipherTable[0].indexOf(keyChar)
        val columnIndex = cipherTable[0].indexOf(messageChar)
        encryptedMessage += cipherTable[rowIndex][columnIndex]
    }

    return encryptedMessage
}

fun printCipherTable(cipherTable: List<String>) {
    println("\nCipher Table:")
    for (row in cipherTable) {
        println(row)
    }
}