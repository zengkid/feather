####About Feather####
[Feather](http://zsoltherpai.github.io/feather) is an ultra-lightweight dependency injection ([JSR-330](https://jcp.org/en/jsr/detail?id=330 "JSR-330"))
library for Java and Android. Dependency injection frameworks are often perceived as "magical" and complex. 
Feather - with just a few hundred lines of code - is probably the easiest, tiniest, most obvious one, 
and is quite efficient too

The fork add below features:

1. auto inject the fields


```java
    feather = Feather.with( /* modules if needed*/ );// if use this, then the speed as fast as original's
```

change to

```java
    feather = Feather.withAutoInjectFields( /* modules if needed*/ ); // will lost some performance.
```

2. add like guice style provider

for example:

```java
    public interface Service {
        public void say();
    }
```

two implements
```java
    public class ServiceImpl1 implements Service {
        public void say() {
            System.out.println("say 1.");
        }
    }

    public class ServiceImpl2 implements Service {
        public void say() {
            System.out.println("say 2.");
        }
    }

```

create a module to binding the implements

```java
    Module myModule = new Module() {
        @Override
        public void configure(Binding binding) {
            binding.bind(UserService.class, UserServiceImpl.class);
            binding.bind(UserService.class, UserServiceImpl2.class, "userService2");
        }
    }
```

usage:
```java
    public class Controller {

        @Inject
        private Service service1;

        @Inject
        @Named("service2")
        private Service service2;


        public void say() {
            service1.say();
            service2.say();
        }

        public static void main(String[] args) {
            Feather feather = Feather.withAutoInjectFields(new Module(){
                @Override
                public void configure(Binding binding) {
                    binding.bind(Service.class, ServiceImpl1.class);
                    binding.bind(Service.class, ServiceImpl2.class, "service2");
                }
            });
            Controller controller = feather.instance(Controller.class);

            controller.say();
        }
    }
```

The output:
```

    say 1.
    say 2.
```

