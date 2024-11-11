# PicPay - Desafio Android

<img src="https://github.com/mobilepicpay/desafio-android/blob/master/desafio-picpay.gif" width="300"/>

Um dos desafios de qualquer time de desenvolvimento é lidar com código legado e no PicPay isso não é diferente. Um dos objetivos de trazer os melhores desenvolvedores do Brasil é atacar o problema. Para isso, essa etapa do processo consiste numa proposta de solução para o desafio abaixo e você pode escolher a melhor forma de resolvê-lo, de acordo com sua comodidade e disponibilidade de tempo:
- Resolver o desafio previamente, e explicar sua abordagem no momento da entrevista.
- Discutir as possibilidades de solução durante a entrevista, fazendo um pair programming (bate-papo) interativo com os nossos devs.

Com o passar do tempo identificamos alguns problemas que impedem esse aplicativo de escalar e acarretam problemas de experiência do usuário. A partir disso elaboramos a seguinte lista de requisitos que devem ser cumpridos ao melhorar nossa arquitetura:

- Em mudanças de configuração o aplicativo perde o estado da tela. Gostaríamos que o mesmo fosse mantido.
- Nossos relatórios de crash têm mostrado alguns crashes relacionados a campos que não deveriam ser nulos sendo nulos e gerenciamento de lifecycle. Gostaríamos que fossem corrigidos.
- Gostaríamos de cachear os dados retornados pelo servidor.
- Haverá mudanças na lógica de negócios e gostaríamos que a arquitetura reaja bem a isso.
- Haverá mudanças na lógica de apresentação. Gostaríamos que a arquitetura reaja bem a isso.
- Com um grande número de desenvolvedores e uma quantidade grande de mudanças ocorrendo testes automatizados são essenciais.
  - Gostaríamos de ter testes unitários testando nossa lógica de apresentação, negócios e dados independentemente, visto que tanto a escrita quanto execução dos mesmos são rápidas.
  - Por outro lado, testes unitários rodam em um ambiente de execução diferenciado e são menos fiéis ao dia-a-dia de nossos usuários, então testes instrumentados também são importantes.

Boa sorte! =)

Ps.: Fique à vontade para editar o projeto inteiro, organização de pastas e módulos, bem como as dependências utilizadas

<hr />

### Resumo das alterações realizadas:

1 - Execução do projeto
 - Atualização de libs, referencias quebradas, namespace, gradle, SDK, java e versão do Kotlin, remoção de libs e plugins depreciados/não usados.

2 - Estruturacao do projeto em camadas
- Seguindo uma proposta de arquitetura clean, temos a divisão por feature, com domain, data e presentation, além dos módulos core para parte de network e banco de dados, visando facilitar a escalabilidade do projeto, desacoplamento através da inversão de dependencias, e implementação de testes.

3 - Melhorias de performance do XML
- Achatamento de hierarquia de views, utilização de ListAdapter para redução de código. Remoção de tags duplicadas (ex: left e start)

4 - Algumas tecnologias escholhidas
- Koin para injeção de dependencias.
- Coroutines/flow para uma abordagem moderna de reatividade.
- ViewBinding no lugar de synthetics.
- Room para banco de dados local.

5 - Navegação
- Cada feature terá uma activity como ponto de entrada. Em caso de modularização, a navegação poderá ocorrer através de deeplinks de feature para feature.

6 - Solução dos problemas propostos
- ViewModel para manter o estado da tela durante a mudança de configuração.
- Gerenciamento correto de lifecycle.
- Dados cacheados no banco interno e servindo como unica fonte de verdade. Um novo request é feito na API caso o usuário esteja online.
- Organização do código com camadas bem definidas para facilitar alterações futuras.
- Testes unitários e instrumentados seguindo o Robot pattern.
