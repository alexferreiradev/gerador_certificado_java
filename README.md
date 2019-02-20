# Gerador de certificados em Java para eventos
Sistema para gerar certificados para eventos. O sistema foi proposto inicialmente para sanar uma necessidade da comunidade java de goiás.

# Objetivo do GOjava
O objetivo do projeto era ajudar o GOJava a agilizar a entrega de certificados para ouvintes de eventos promovidos pela comunidade. Portanto, o gerador gera um PDF pronto para ser enviado para os ouvintes.
Exemplo de PDF gerado: [Exemplo.pdf](https://github.com/alexferreiradev/gerador_certificado_java/blob/master/certificados/certificado_Alex.pdf)

# How to use
Baixe o jar da [última versão estável](https://github.com/alexferreiradev/gerador_certificado_java/releases/latest), crie um arquivo csv semelhante a este [arquivo_exemplo.csv](https://github.com/alexferreiradev/gerador_certificado_java/blob/master/test_participantes.csv) e uma imagem para background que tenha as assinaturas do palestrante e presidente do executor do evento. Depois do ambiente criado, execute: 

`java -jar <jar_baixado_lastest_version.jar> <Nome do executor do evento> <nome do arquivo csv.csv> <nome do arquivo de imagem de background.png>`

Exemplo de comando: `java -jar certificate-generator-1.0.0.jar Gojava test_list.csv background_gojava_1-0-0.png`

# Contributions
Envie um e-mail para arf92@live.com e mostre seu interesse em contribuir e suas ideias para contribuir.

# Licença
Utilize onde quizer e para quem quizer. Caso seja alguma comunidade, incentive contribuindo com pull requests.
