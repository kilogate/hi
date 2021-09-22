package com.kilogate.hi.javaagent.client;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * AgentMainClientSupport
 *
 * @author kilogate
 * @create 2021/9/22 15:34
 **/
public class AgentMainClientSupport {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // 代理程序路径
        String agentPath = "/Users/xxx/.m2/repository/com/kilogate/hi-javaagent-agentmain/1.0-SNAPSHOT/hi-javaagent-agentmain-1.0-SNAPSHOT.jar";

        // 获取所有 JVM 实例
        List<VirtualMachineDescriptor> descriptorList = VirtualMachine.list();

        for (VirtualMachineDescriptor descriptor : descriptorList) {
            // 判断如果是 AgentMainClient，就加载代理程序
            if (descriptor.displayName().equals(AgentMainClient.class.getName())) {
                VirtualMachine virtualMachine = VirtualMachine.attach(descriptor);
                virtualMachine.loadAgent(agentPath, "hello");
            }
        }
    }
}
