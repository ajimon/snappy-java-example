package com.ajimon.snappy;

import static org.xerial.snappy.Snappy.compress;
import static org.xerial.snappy.Snappy.uncompress;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SnappyCompressionDemo {

    public static void main(String[] args)
            throws IOException, URISyntaxException {

        ClassLoader classLoader = SnappyCompressionDemo.class.getClassLoader();
        URI uri = classLoader.getResource("demo.txt").toURI();
        Path path = Paths.get(uri);
        byte[] beforeBytes = Files.readAllBytes(path);

        // compress
        System.out.println(
                "Before snappy compress size：" + beforeBytes.length + " bytes");
        long startTime1 = System.currentTimeMillis();
        byte[] afterBytes = compress(beforeBytes);
        long endTime1 = System.currentTimeMillis();
        System.out.println(
                "after snappy compress size：" + afterBytes.length + " bytes");
        System.out.println("snappy compress time elapsed："
                + (endTime1 - startTime1) + "ms");

        // uncompress
        long startTime2 = System.currentTimeMillis();
        byte[] resultBytes = uncompress(afterBytes);
        System.out.println(
                "snappy uncompress size：" + resultBytes.length + " bytes");
        long endTime2 = System.currentTimeMillis();
        System.out.println(
                "uncompress time elapsed：" + (endTime2 - startTime2) + "ms");

    }

}
