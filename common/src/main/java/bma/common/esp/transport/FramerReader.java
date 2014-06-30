package bma.common.esp.transport;

public class FramerReader {
	
	public static final int decodeFrameSize(final byte[] buf) {
		return (
				((buf[1] & 0xff) << 16)| 
				((buf[2] & 0xff) << 8) | 
				((buf[3] & 0xff)));
	}

	public static final void encodeFrameSize(final int frameSize,
			final byte[] buf) {
		buf[1] = (byte) (0xff & (frameSize >> 16));
		buf[2] = (byte) (0xff & (frameSize >> 8));
		buf[3] = (byte) (0xff & (frameSize));
	}

}
