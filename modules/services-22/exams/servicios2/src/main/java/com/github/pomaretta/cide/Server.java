package com.github.pomaretta.cide;

import com.github.pomaretta.cide.infrastructure.CacheService;
/**
 * @author Carlos Pomares <https://www.github.com/pomaretta>
 */
public class Server {

    public static void main(String[] args) throws Exception {

        int port = 56001;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        CacheService service = new CacheService(port);
        service.start();
    }

}
