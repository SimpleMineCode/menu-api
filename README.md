## Use in your projects

```XML
<repository>
  <id>smcode-repo-releases</id>
  <name>SMCode Repository</name>
  <url>https://repo.smcode.io/releases</url>
</repository>

<dependency>
  <groupId>io.smcode</groupId>
  <artifactId>menu-api</artifactId>
  <version>{version}</version>
</dependency>
```

## Create a GUI
To make a GUI you have to create a class and let it extend `SimpleMenu`:

```JAVA
public class KillMenu extends SimpleMenu {
    public KillMenu() {
        super(Rows.THREE, "End your suffering");
    }

    @Override
    public void onSetItems() {
        // This method can be used to add items to the GUI
        final ItemStack killItem = new ItemStack(Material.BARRIER);

        setItem(13, killItem, player -> {
            // This will be executed when the player clicks this item
            player.sendMessage("You clicked this item");
        });
    }
}
```

