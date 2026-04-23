#!/usr/bin/env python3
"""Generate simple launcher icons for the app."""
import struct, zlib, os

def create_png(width, height, color_rgba):
    def chunk(name, data):
        c = name + data
        return struct.pack('>I', len(data)) + c + struct.pack('>I', zlib.crc32(c) & 0xffffffff)
    sig = b'\x89PNG\r\n\x1a\n'
    ihdr_data = struct.pack('>IIBBBBB', width, height, 8, 2, 0, 0, 0)
    ihdr = chunk(b'IHDR', ihdr_data)
    raw = b''
    r, g, b, a = color_rgba
    for _ in range(height):
        raw += b'\x00' + bytes([r, g, b] * width)
    compressed = zlib.compress(raw)
    idat = chunk(b'IDAT', compressed)
    iend = chunk(b'IEND', b'')
    return sig + ihdr + idat + iend

sizes = {
    'mipmap-mdpi': 48, 'mipmap-hdpi': 72, 'mipmap-xhdpi': 96,
    'mipmap-xxhdpi': 144, 'mipmap-xxxhdpi': 192
}
base = os.path.dirname(os.path.abspath(__file__))
res = os.path.join(base, 'app', 'src', 'main', 'res')
for folder, size in sizes.items():
    path = os.path.join(res, folder)
    os.makedirs(path, exist_ok=True)
    png = create_png(size, size, (27, 138, 90, 255))
    with open(os.path.join(path, 'ic_launcher.png'), 'wb') as f:
        f.write(png)
    with open(os.path.join(path, 'ic_launcher_round.png'), 'wb') as f:
        f.write(png)
print("Icons generated.")
