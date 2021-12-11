	program squidBingo
	use, intrinsic :: iso_fortran_env, only: error_unit
    IMPLICIT NONE
	

	INTEGER::D(100)
	INTEGER*4::i, j
	type :: draw_type
    INTEGER   :: draw(100)
	CHARACTER(len=1) :: comma
	end type draw_type

	type(draw_type) :: drawSequence(12)
	integer            :: file_unit
	integer            :: rc

		open (action='read', file='input', iostat=rc, newunit=file_unit)

		if (rc /= 0) stop
		i = 0
		j = 0
		do i = 1, 12, 1
!			do j = 1, 12, 1
				read (file_unit, *, iostat=rc) drawSequence(i)
				print *, drawSequence(i)%comma
!				if ((drawSequence(i)%comma) == ",")
				if (rc /= 0) exit
!			end do
		end do

		close (file_unit)

		do j = 1, 100, 1
			D(j) = drawSequence(1)%draw(j)
			print *, D(j)
		end do
		print *, drawSequence(1)%draw(1)
		

	end program squidBingo
