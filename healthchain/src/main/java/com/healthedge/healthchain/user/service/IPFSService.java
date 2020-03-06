package com.healthedge.healthchain.user.service;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class IPFSService {

    @Value(value = "${ipfs.url}")
    private String IPFS_URL;

    private final Logger LOGGER = LoggerFactory.getLogger(IPFSService.class);

    public File prepareForIPFSInjestion(String payload) throws FileNotFoundException {
        PrintWriter out=null;
        try {
            out = new PrintWriter("filename.txt");
            out.println(payload);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return new File("filename.txt");
    }

    public String addFileToIPFS(File file) throws IOException {
        LOGGER.info("IPFS URL:" + IPFS_URL);
        IPFS ipfs = new IPFS(IPFS_URL);
        NamedStreamable.FileWrapper ipfsFile = new NamedStreamable.FileWrapper(file);
        MerkleNode node = ipfs.add(ipfsFile).get(0);
        LOGGER.info("Merkle Node: " + node.toString());
        LOGGER.info("Hash: " + node.hash.toBase58());
        file.delete();
        return node.hash.toBase58();
    }


    public byte[] getData(String hash) throws IOException {
        IPFS ipfs = new IPFS(IPFS_URL);
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] contents = ipfs.cat(filePointer);
        return contents;
    }

}
