package com.sistema.apirest.auth;

public class JwtConfig {
	
	public static final  String LLAVE_SECRETA = "alguna.llave.secreta";
	
	public static final String RSA_PRIVADA ="-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpQIBAAKCAQEA6E7+nI8aamcg9ID2rmT2cbP2iihzvbH4TKOAilde8GyPzVzy\r\n"
			+ "EJTqb76cGpSAG/MjihrHl53rquBt0JjhGVg21eqON0w2hpIgXzZmty6Txuk+czlN\r\n"
			+ "Kz12MfyFKVJ2WEyWNCbCfW4MUa9LI8u7WzebaYSLXf6Lqt4xzruLm0aPUfgB5dhW\r\n"
			+ "pgL2iv1dBwzhDGn2M7d0JGfUiX/Qn6WeWhMmIgdje6lm4bn+3VZT3kMm9BL5/TB8\r\n"
			+ "HGw8VDEaIheegYe1pEnymdWI8eJelW/UHlaoxRqJsHEDfjiKdC5TTA4w4I9Wh7Yd\r\n"
			+ "PFjxlxzPxRf36b7d7NVuOei52Ft9U2b4pKWlfQIDAQABAoIBAQCz97aR7N7m/Vvv\r\n"
			+ "bxUrx3WQYn2lTG9VztDv9l53TfZzxXal/tJcfDEePolKoi6Y+IRY0iQ71hCjgVgx\r\n"
			+ "mK4R52Ab2qkwN6+qEvxXJpruYHwFRd6mfwbEb8noMZurhoG+KB7Y6goUB7Qtd+J7\r\n"
			+ "KbLMmMiiNxeMyszCo33sjSg7M6+GLGTA5WfE5tkd9kCjT4+MtNmibKZxLm7mOxLT\r\n"
			+ "Q/3HkxjRR0btkNB7zH5oeF7LRrjOpYRh5Vf2bK4PF9muhm46WQhRBdnnuJnmssHQ\r\n"
			+ "jRw2BC+T+sk8zY/WOyNdvZdZuuLSGD+0XTJuwtt4SChe1qv8Zq0ooL5riuNRi1qC\r\n"
			+ "hiI32aJ1AoGBAPmOgn/ChtCW/wAv1xJvV87FoOmdzIkV8w+j2eWc7auBAnZ5qECp\r\n"
			+ "k+e1tjqT1Qt5Xv+AqejHSr2B6JgZOl7r9PRwrtskfxWcayQylBebqHSlVzOrHzea\r\n"
			+ "xHT2X4ujWhtoHCDyoeBkd3zpxj56W2qZBtBvhLUyWstI4jmUaxcj5t6vAoGBAO5O\r\n"
			+ "evZOKSM7MsitKCX5ZcNLbRtrs6nb7KwYzLrX+hF9vPeC+fa+VAOi7LMFReey14A+\r\n"
			+ "v9suQw8oLGDdZPteai+7gPAXM49gmH/Q/dQFlyMqiVbHeOk10ExykFDq3yboHYW4\r\n"
			+ "gu0XGp5bClsQBPMSky+dKaPbDMupLkQVoG0FommTAoGAMkpkxAhYkLqNIuWBKwbj\r\n"
			+ "JBkxh0K0kD+rHtC/PYV1Y+V4sJVtsbI6yywUUBOwkEJiQY0VMILQ7gW68MPMvopA\r\n"
			+ "b+4xXpopPhqqgL3smCFXPkJ/ozrt7I+03Xk04fgGVZWSbZVXx9eXOMYHnFmeCIpU\r\n"
			+ "FDcVAErofyTt6fqd5ijnAqMCgYEAr64XpCC23wwzQhG1OVmEldazFrXte1lwPucB\r\n"
			+ "dvTlm6NwfZ+HG0nwscWYbdmDUW5cxGR+F90g/rmFDdvpKA7QhWPbiCPylM6HXiL6\r\n"
			+ "HSYJ55jiKqFfF73eU51Zmu2rmQkhSoVdr9+KdhdzqPJEU9Xrv/xCyFMiObn9K6At\r\n"
			+ "5nFy7BUCgYEA+UD8Dy230vXOLUEtjQrlNs5tVE1mUeLy1BxQdI5deVhMWnXKLZdJ\r\n"
			+ "GHgxryLYpuW20jlSmGFvDrR/qrB8qXs/sLAinnJlu2GFGkUq0gqf5SrALKuHZKKA\r\n"
			+ "tz9F/n4kDK8tsX4d4aq64/ZsfAOvv0Dr0GMBDL3BSKkZdxcw7KWp9/g=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	
	public static final String RSA_PUBLICA="-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6E7+nI8aamcg9ID2rmT2\r\n"
			+ "cbP2iihzvbH4TKOAilde8GyPzVzyEJTqb76cGpSAG/MjihrHl53rquBt0JjhGVg2\r\n"
			+ "1eqON0w2hpIgXzZmty6Txuk+czlNKz12MfyFKVJ2WEyWNCbCfW4MUa9LI8u7Wzeb\r\n"
			+ "aYSLXf6Lqt4xzruLm0aPUfgB5dhWpgL2iv1dBwzhDGn2M7d0JGfUiX/Qn6WeWhMm\r\n"
			+ "Igdje6lm4bn+3VZT3kMm9BL5/TB8HGw8VDEaIheegYe1pEnymdWI8eJelW/UHlao\r\n"
			+ "xRqJsHEDfjiKdC5TTA4w4I9Wh7YdPFjxlxzPxRf36b7d7NVuOei52Ft9U2b4pKWl\r\n"
			+ "fQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
