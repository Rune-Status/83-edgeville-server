package edgeville.net.message.game.encoders;

import edgeville.io.RSBuffer;
import edgeville.model.Tile;
import edgeville.model.entity.Player;

/**
 * @author Simon on 8/18/2015.
 */
public class SetMapBase implements Command {

	public int x;
	public int z;

	public SetMapBase(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public SetMapBase(Player player, int x, int z) {
		Tile base = player.activeMap();
		int relx = x - base.x;
		int relz = z - base.z;
		this.x = relx / 8 * 8;
		this.z = relz / 8 * 8;
	}

	public SetMapBase(Player player, Tile t) {
		Tile base = player.activeMap();
		int relx = t.x - base.x;
		int relz = t.z - base.z;
		this.x = relx / 8 * 8;
		this.z = relz / 8 * 8;
	}

	@Override
	public RSBuffer encode(Player player) {
		RSBuffer buffer = new RSBuffer(player.channel().alloc().buffer(3)).packet(154);

		buffer.writeByteS(z);
		buffer.writeByteA(x);

		return buffer;
	}

}
