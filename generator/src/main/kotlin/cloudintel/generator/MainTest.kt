@Test fun `CLI writes file`() {
    val file = File.createTempFile("cur", ".csv")
    main(arrayOf("-p","aws","-n","10","-o",file.absolutePath))
    assertTrue(file.readLines().size == 11) // 10 data + header
}
