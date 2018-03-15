def gui_to_string(value):
    if value is None:
        return ''

    return str(value)


def append_file_extension(file_name, extension):
        if not file_name.endswith('.' + extension):
            return '{}.{}'.format(file_name, extension)

        return file_name
