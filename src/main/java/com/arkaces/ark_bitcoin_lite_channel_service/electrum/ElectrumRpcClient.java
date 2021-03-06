package com.arkaces.ark_bitcoin_lite_channel_service.electrum;

import com.arkaces.aces_server.common.json.NiceObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * https://electrumx.readthedocs.io/en/latest/protocol.html
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ElectrumRpcClient {

    private final List<ElectrumNode> electrumSeedPeers;
    private final NiceObjectMapper objectMapper = new NiceObjectMapper(new ObjectMapper());

    public String sendCommand(String method, List<Object> params) {
        ElectrumNode electrumNode = getRandomElectrumNode();
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(electrumNode.getHost(), electrumNode.getPort());
            Socket socket = new Socket();
            socket.connect(socketAddress, 2000);
            socket.setSoTimeout(2000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));

            String serverVersionRequest = getRequestJson("server.version", Arrays.asList("nc", "1.4"));
            log.info("Electrum request to {}: {}", electrumNode.getHost(), serverVersionRequest);
            out.write(serverVersionRequest + "\r\n");
            out.flush();

            String serverVersionResponse = in.readLine();
            log.info("Response: " + serverVersionResponse);

            String commandRequest = getRequestJson(method, params);
            log.info("Request: " + commandRequest);
            out.write(commandRequest + "\r\n");
            out.flush();

            String commandResponse = in.readLine();
            log.info("Response: " + commandResponse);

            out.close();
            in.close();

            return commandResponse;
        }
        catch (IOException e) {
            log.info("Exception connecting to electrum node: " + electrumNode.toString());
            throw new RuntimeException("IO Exception thrown", e);
        }
    }

    private ElectrumNode getRandomElectrumNode() {
        return electrumSeedPeers.get(RandomUtils.nextInt(electrumSeedPeers.size()));
    }

    private String getRequestJson(String method, List<Object> params) {
        RpcRequest request = new RpcRequest();
        request.setMethod(method);
        request.setParams(params);

        return objectMapper.writeValueAsString(request);
    }

}
